package com.rovitapps.google.books.integration.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils {

    private static String ZONE_ID = "America/Sao_Paulo";

    public static Date getDateNow(){
        return Date.from(getZonedDateTimeNow().toInstant());
    }

    public static Date getDateYesterday(){
        return Date.from(getZonedDateTimeNow().minusDays(1).toInstant());
    }

    public static ZonedDateTime getZonedDateTimeNow(){
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime date = ldt.atZone(ZoneId.of(ZONE_ID));
        return date;
    }

    public static ZonedDateTime getZonedDateTimeToday(){
        LocalDateTime ldt = LocalDate.now().atStartOfDay();
        ZonedDateTime date = ldt.atZone(ZoneId.of(ZONE_ID));
        return date;
    }

}
