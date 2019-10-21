package com.rovitapps.google.books.integration.api;

import com.rovitapps.google.books.integration.api.model.Book;
import com.rovitapps.google.books.integration.api.repository.BookRepository;
import com.rovitapps.google.books.integration.api.util.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Bootstrap implements ApplicationRunner {

    private static final Logger LOGGER = LogManager.getLogger(Bootstrap.class);

    private final BookRepository bookRepository;

    @Autowired
    public Bootstrap(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void run(ApplicationArguments args) {
        LOGGER.info("BOOTSTRAP: Initializing...");

        createBook("Patrick Rothfuss: The Name of the Wind");

        LOGGER.info("BOOTSTRAP: End!");
    }
    private Book createBook(String title){
        Book book = bookRepository.findByTitle(title);
        if(book == null)
            return bookRepository.save(new Book("A0001", title, DateUtils.getDateNow()));
        return null;
    }

}