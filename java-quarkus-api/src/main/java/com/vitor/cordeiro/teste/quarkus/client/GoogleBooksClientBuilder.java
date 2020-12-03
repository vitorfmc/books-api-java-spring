package com.vitor.cordeiro.teste.quarkus.client;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import java.net.MalformedURLException;
import java.net.URL;

@ApplicationScoped
public class GoogleBooksClientBuilder {
    public GoogleBooksClient build(String url) throws MalformedURLException {
        return RestClientBuilder.newBuilder()
                .baseUrl(new URL(url))
                .register(GoogleBooksClientMapper.class)
                .build(GoogleBooksClient.class);
    }
}
