package com.rovitapps.google.books.integration.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rovitapps.google.books.integration.api.config.CronConfig;
import com.rovitapps.google.books.integration.api.exception.DataNotFoundException;
import com.rovitapps.google.books.integration.api.exception.DataValidationException;
import com.rovitapps.google.books.integration.api.exception.ResponseException;
import com.rovitapps.google.books.integration.api.model.Book;
import com.rovitapps.google.books.integration.api.model.dto.BookCreateDTO;
import com.rovitapps.google.books.integration.api.repository.BookRepository;
import com.rovitapps.google.books.integration.api.util.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BookService {

    private static final Logger LOGGER = LogManager.getLogger(CronConfig.class);

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    private BookRepository repository;

    @Value( "${google.books.url}" )
    private String googleBooksURL;

    @Value( "${google.books.timeout}" )
    private int googleBooksTimeout;
    
    public Book save(@NotNull(message = "Request body not informed") @Valid BookCreateDTO dto)
            throws DataValidationException, DataNotFoundException {
        try{
            Book oldBook = findByTitle(dto.getTitle());
            if(oldBook != null)
                throw new DataValidationException(messageSource.getMessage("book.error.already.exists", null,
                        null, Locale.getDefault()));

            Book book = new Book(dto.getLibraryCode(), dto.getTitle(),
                    (new SimpleDateFormat("dd/MM/yyyy")).parse(dto.getCatalogingDate()));
            setGoogleInformation(book);

            validate(book);

            return repository.save(book);

        }catch (ParseException e){
            throw new DataValidationException(messageSource.getMessage("book.error.invalid.date", null,
                    null, Locale.getDefault()));
        }
    }

    public Book update(@NotNull(message = "The book ID is mandatory") String id,
                       @NotNull(message = "Request body not informed") @Valid BookCreateDTO dto)
            throws DataValidationException, DataNotFoundException {

        try{
            Book oldBook = findById(id);
            if(oldBook == null)
                throw new DataValidationException(messageSource.getMessage("book.error.not.exist", null,
                        null, Locale.getDefault()));

            Book book = findByTitle(dto.getTitle());
            if(book !=null && !book.getId().equals(id))
                throw new DataValidationException(messageSource.getMessage("book.error.already.exists", null,
                        null, Locale.getDefault()));

            oldBook.setLibraryCode(dto.getLibraryCode());
            oldBook.setTitle(dto.getTitle());
            oldBook.setCatalogingDate((new SimpleDateFormat("dd/MM/yyyy")).parse(dto.getCatalogingDate()));
            setGoogleInformation(oldBook);

            validate(oldBook);

            return repository.save(oldBook);

        }catch (ParseException e){
            throw new DataValidationException(messageSource.getMessage("book.error.invalid.date", null,
                    null, Locale.getDefault()));
        }
    }

    public void delete(String id) throws DataValidationException, DataNotFoundException {
        Book book = findById(id);
        if(book == null)
            throw new DataValidationException(messageSource.getMessage("book.error.not.exist",
                    null, null, Locale.getDefault()));

        repository.delete(book);
    }

    public Book findByTitle(String title) throws DataValidationException {
        if(title == null){
            String message = messageSource.getMessage("book.error.not.informed",
                    null, null, Locale.getDefault());
            throw new DataValidationException(message);
        }

        return repository.findByTitle(title);
    }

    public Book findById(String id) throws DataNotFoundException {
        if(id == null){
            String message = messageSource.getMessage("book.error.not.informed",
                    null, null, Locale.getDefault());
            throw new DataNotFoundException(message);
        }

        return repository.findById(id, Book.class);
    }

    public Page<Book> findAll(int limit, int offset, String q) throws DataValidationException {

        validateLimitAndOffset(limit, offset);

        Page<Book> data;
        if(q != null && !q.isEmpty())
            data = repository.findAllByCriteria(PageRequest.of(offset, limit), q);
        else
            data = repository.findAll(PageRequest.of(offset, limit));

        return data;
    }

    private void validateLimitAndOffset(Integer limit, Integer offset) throws DataValidationException {

        List<String> errors = new ArrayList<>();

        if(limit == null || limit <= 0){
            errors.add(messageSource.getMessage("generic.error.limit",
                    null, Locale.getDefault()));
        }

        if(offset == null || offset < 0){
            errors.add(messageSource.getMessage("generic.error.offset",
                    null, Locale.getDefault()));
        }

        if(!errors.isEmpty())
            throw new DataValidationException(errors);
    }

    private void validate(Object arg) throws DataValidationException {
        List<String> errors = new ArrayList<>();

        ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        List<ConstraintViolation<Object>> violations = new ArrayList<>(validator.validate(arg));

        if(!violations.isEmpty()){
            for(ConstraintViolation<Object> currentError : violations){
                errors.add(currentError.getMessage());
            }

            throw new DataValidationException(errors);
        }
    }

    /**
     * Integrates with Google to verify if the books that are X Days before now needs to be updated.
     * @param days Quantity of days before today at the beginning of the day
     * @param limit Limit of Books to be updated
     */
    public void updateBooks(int days, int limit) {
        Page<Book> books = repository.findByUpdateDateBefore(PageRequest.of(0, limit),
                DateUtils.getDateYesterday());

        for(Book book: books)
            setGoogleInformation(book);
    }

    public Book setGoogleInformation(Book book){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate
                .getRequestFactory();
        rf.setReadTimeout(googleBooksTimeout);
        rf.setConnectTimeout(googleBooksTimeout);

        String url = googleBooksURL + "/?q=+intitle:" + book.getTitle();

        try{
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (!response.getStatusCode().is2xxSuccessful())
                throw new ResponseException(messageSource.getMessage("generic.error.offset",
                        null, Locale.getDefault()), response.getBody());

            JsonObject convertedObject = new Gson().fromJson(response.getBody(), JsonObject.class);
            JsonArray items = convertedObject.getAsJsonArray("items");
            JsonObject googleBook = null;
            int i = 0;

            while(i < items.size() && googleBook == null){
                JsonObject item = items.get(i).getAsJsonObject();
                String googleTitle = item.get("volumeInfo").getAsJsonObject().get("title").getAsString();
                if(googleTitle.equals(book.getTitle())){
                    googleBook = item;
                }else{
                    i++;
                }
            }

            if(googleBook != null){
                //TO DO
            }

        }catch (ResponseException e){
            LOGGER.error("[GOOGLE INTEGRATION] Error: " + e.getData(), e);

        }catch (Exception e){
            LOGGER.error("[GOOGLE INTEGRATION] Error: " + e, e);
        }

        return book;
    }
}
