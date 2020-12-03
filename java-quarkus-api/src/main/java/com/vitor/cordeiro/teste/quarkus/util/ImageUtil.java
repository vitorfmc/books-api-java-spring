package com.vitor.cordeiro.teste.quarkus.util;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageUtil {

    public static String urlToBase64(String imageUrl) throws IOException {
        String resp = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try{
            URL url = new URL(imageUrl);

            URLConnection ucon = url.openConnection();
            is = ucon.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();

            resp = Base64.encodeBase64String(baos.toByteArray());
        }catch (Exception e){

        }finally {
            if(is != null) is.close();
            if(baos != null) baos.close();
        }

        return resp;
    }

}
