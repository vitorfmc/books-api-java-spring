package com.vitor.cordeiro.teste.quarkus.client;

import com.vitor.cordeiro.teste.quarkus.exception.InvalidCredentialResponseStatusException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;

@Priority(4000)
public class GoogleBooksClientMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {
        int status = response.getStatus();
        String msg = getBody(response);

        RuntimeException re;
        switch (status) {
            case 401:
                re = new InvalidCredentialResponseStatusException(msg);
                break;
            default:
                re = new WebApplicationException(msg, status);
        }
        return re;
    }

    private String getBody(Response response) {
        ByteArrayInputStream is = (ByteArrayInputStream) response.getEntity();
        byte[] bytes = new byte[is.available()];
        is.read(bytes,0,is.available());
        String body = new String(bytes);
        return body;
    }

}
