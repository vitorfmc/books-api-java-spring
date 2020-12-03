package com.vitor.cordeiro.teste.quarkus.service;

import com.vitor.cordeiro.teste.quarkus.client.model.GoogleBooksResponse;
import com.vitor.cordeiro.teste.quarkus.exception.DataValidationException;
import com.vitor.cordeiro.teste.quarkus.exception.GoogleApiGenericException;

public interface GoogleBooksService {

    GoogleBooksResponse getBookByTitle(String q, Integer limit, Integer offset) throws GoogleApiGenericException, DataValidationException;

}
