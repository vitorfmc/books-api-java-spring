package com.vitor.cordeiro.teste.quarkus.client.model;

public class GoogleBooksIndustryIdentifier {

    private String type;
    private String identifier;

    public GoogleBooksIndustryIdentifier(){}

    public GoogleBooksIndustryIdentifier(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
