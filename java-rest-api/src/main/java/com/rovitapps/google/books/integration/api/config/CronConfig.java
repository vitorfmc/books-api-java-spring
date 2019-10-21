package com.rovitapps.google.books.integration.api.config;

import com.rovitapps.google.books.integration.api.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CronConfig {
    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private BookService service;

    @Value( "${google.books.cronActive}" )
    private boolean bookCronActivated;

    @Value( "${books.query.limit}" )
    private int booksQueryLimit;

    @Value( "${books.query.days}" )
    private int booksQueryDays;

    private static final Logger LOGGER = LogManager.getLogger(CronConfig.class);

    //Every 1 hour
    @Scheduled(cron = "0 1 * * * *", zone = TIME_ZONE)
    public void scheduleTaskLoadUpdateStoreFromMapStore() {
        try{
           if(bookCronActivated)
                service.updateBooks(booksQueryDays, booksQueryLimit);

            LOGGER.error("[UPDATE_BOOK_CRON] Executed");

        }catch (Exception e){
            LOGGER.error("[UPDATE_BOOK_CRON] Error: " + e.getMessage(), e);
        }
    }
}
