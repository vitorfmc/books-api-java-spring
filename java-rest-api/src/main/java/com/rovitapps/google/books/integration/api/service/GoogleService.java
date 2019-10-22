package com.rovitapps.google.books.integration.api.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rovitapps.google.books.integration.api.config.CronConfig;
import com.rovitapps.google.books.integration.api.exception.ResponseException;
import com.rovitapps.google.books.integration.api.model.Book;
import com.rovitapps.google.books.integration.api.model.Image;
import com.rovitapps.google.books.integration.api.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class GoogleService {

    private static final Logger LOGGER = LogManager.getLogger(CronConfig.class);

    @Autowired
    protected MessageSource messageSource;

    @Value( "${google.books.url}" )
    private String googleBooksURL;

    @Value( "${google.books.timeout}" )
    private int googleBooksTimeout;

    public Book setGoogleInformation(Book book) {

        try{
            JsonObject response = doGet(googleBooksURL + "/?q=+intitle:" + book.getTitle());
            JsonObject googleBook = findBook(book.getTitle(), response.getAsJsonArray("items"));

            if(googleBook != null){
                try{
                    List<String> authors = new ArrayList<>();
                    JsonArray authorsArray = googleBook.get("volumeInfo").getAsJsonObject().get("authors").getAsJsonArray();
                    for(int count = 0; count < authorsArray.size(); count++){
                        authors.add(authorsArray.get(count).getAsString());
                    }
                    book.setAuthors(authors);
                }catch (Exception e){
                    LOGGER.error("[GOOGLE INTEGRATION] Error: " + e.getMessage(), e);
                }

                try{
                    String imageUrl = googleBook.get("volumeInfo").getAsJsonObject()
                            .get("imageLinks").getAsJsonObject().get("thumbnail").getAsString();

                    String base64Img = Utils.urlToBase64(imageUrl);

                    book.getImages().add(new Image(imageUrl, base64Img));

                }catch (Exception e){
                    LOGGER.error("[GOOGLE INTEGRATION] Error: " + e.getMessage(), e);
                }

                try{
                    book.setGoogleBookId(googleBook.get("id").getAsString());
                }catch (Exception e){
                    LOGGER.error("[GOOGLE INTEGRATION] Error: " + e.getMessage() ,e);
                }
            }

        }catch (ResponseException e){
            LOGGER.error("[GOOGLE INTEGRATION] Error: " + e.getData(), e);

        }catch (Exception e){
            LOGGER.error("[GOOGLE INTEGRATION] Error: " + e, e);
        }

        return book;
    }

    private JsonObject findBook(String title, JsonArray items){
        int i = 0;
        JsonObject googleBook = null;
        while(i < items.size() && googleBook == null){
            JsonObject item = items.get(i).getAsJsonObject();
            String googleTitle = item.get("volumeInfo").getAsJsonObject().get("title").getAsString();
            if(googleTitle.equals(title)){
                googleBook = item;
            }else{
                i++;
            }
        }

        return googleBook;
    }

    private JsonObject doGet(String url) throws ResponseException {
        ResponseEntity<String> response = getRestTemplate().getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful())
            throw new ResponseException(messageSource.getMessage("generic.error.offset",
                    null, Locale.getDefault()), response.getBody());

        return new Gson().fromJson(response.getBody(), JsonObject.class);
    }

    private RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate
                .getRequestFactory();
        rf.setReadTimeout(googleBooksTimeout);
        rf.setConnectTimeout(googleBooksTimeout);

        return restTemplate;
    }

}
