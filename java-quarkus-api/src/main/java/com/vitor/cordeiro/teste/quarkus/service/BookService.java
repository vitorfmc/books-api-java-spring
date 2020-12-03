package com.vitor.cordeiro.teste.quarkus.service;

import com.vitor.cordeiro.teste.quarkus.entity.Book;
import com.vitor.cordeiro.teste.quarkus.exception.DataValidationException;
import com.vitor.cordeiro.teste.quarkus.exception.GoogleApiGenericException;

import java.util.List;

public interface BookService {

    List<Book> getBook(String q, Integer limit, Integer offset) throws GoogleApiGenericException, DataValidationException;

}
