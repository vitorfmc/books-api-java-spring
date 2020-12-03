package com.rovitapps.google.books.integration.api.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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

    public static String urlToBase64(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);

        URLConnection ucon = url.openConnection();
        InputStream is = ucon.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = is.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, read);
        }
        baos.flush();

        return Base64.encodeBase64String(baos.toByteArray());
    }
}
