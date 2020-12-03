package com.vitor.cordeiro.teste.quarkus.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Image {

    private String originalUrl;
    private String base64;

    public Image(){}

    public Image(String originalUrl, String base64) {
        this.originalUrl = originalUrl;
        this.base64 = base64;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
