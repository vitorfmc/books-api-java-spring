package com.vitor.cordeiro.teste.quarkus.client.model;

public class GoogleBooksResponseItem {

    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private GoogleBooksVolumeInfo volumeInfo;

    public GoogleBooksResponseItem() {
    }

    public GoogleBooksResponseItem(String kind, String id, String etag, String selfLink, GoogleBooksVolumeInfo volumeInfo) {
        this.kind = kind;
        this.id = id;
        this.etag = etag;
        this.selfLink = selfLink;
        this.volumeInfo = volumeInfo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public GoogleBooksVolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(GoogleBooksVolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

}
