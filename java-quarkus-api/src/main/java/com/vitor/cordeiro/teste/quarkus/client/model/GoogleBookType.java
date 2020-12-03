package com.vitor.cordeiro.teste.quarkus.client.model;

public class GoogleBookType {

    private Boolean isAvailable;
    private String acsTokenLink;

    public GoogleBookType(){}

    public GoogleBookType(Boolean isAvailable, String acsTokenLink) {
        this.isAvailable = isAvailable;
        this.acsTokenLink = acsTokenLink;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }
}
