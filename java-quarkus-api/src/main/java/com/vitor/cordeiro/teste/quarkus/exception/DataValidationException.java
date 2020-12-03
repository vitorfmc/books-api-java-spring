package com.vitor.cordeiro.teste.quarkus.exception;

import java.util.List;

public class DataValidationException extends Exception {

    private List<String> messages;

    public DataValidationException(List<String> messages){
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
