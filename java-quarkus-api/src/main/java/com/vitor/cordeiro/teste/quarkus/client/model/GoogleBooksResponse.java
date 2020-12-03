package com.vitor.cordeiro.teste.quarkus.client.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class GoogleBooksResponse {

    private String kind;
    private Integer totalItems;
    private List<GoogleBooksResponseItem> items;

    public GoogleBooksResponse(){}

    public GoogleBooksResponse(String kind, Integer totalItems, List<GoogleBooksResponseItem> items) {
        this.kind = kind;
        this.totalItems = totalItems;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<GoogleBooksResponseItem> getItems() {
        return items;
    }

    public void setItems(List<GoogleBooksResponseItem> items) {
        this.items = items;
    }
}
