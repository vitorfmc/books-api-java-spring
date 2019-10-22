package com.rovitapps.google.books.integration.api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Utils {

    private static String ZONE_ID = "America/Sao_Paulo";

    public static Date getDateNow(){
        return Date.from(getZonedDateTimeNow().toInstant());
    }

    public static Date getDateMinus(int days){
        return Date.from(getZonedDateTimeNow().minusDays(days).toInstant());
    }

    public static ZonedDateTime getZonedDateTimeNow(){
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime date = ldt.atZone(ZoneId.of(ZONE_ID));
        return date;
    }

    public static String urlToBase64(String imageUrl) {
        return null;
    }
}
