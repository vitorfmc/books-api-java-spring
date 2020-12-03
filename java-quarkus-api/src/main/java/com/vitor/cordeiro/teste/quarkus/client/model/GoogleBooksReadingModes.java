package com.vitor.cordeiro.teste.quarkus.client.model;

public class GoogleBooksReadingModes {

    private Boolean text;
    private Boolean image;

    public GoogleBooksReadingModes(){}

    public GoogleBooksReadingModes(Boolean text, Boolean image) {
        this.text = text;
        this.image = image;
    }

    public Boolean getText() {
        return text;
    }

    public void setText(Boolean text) {
        this.text = text;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }
}
