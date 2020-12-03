package com.vitor.cordeiro.teste.quarkus.client.model;

import java.util.List;

public class GoogleBooksVolumeInfo {

    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<GoogleBooksIndustryIdentifier> industryIdentifiers;
    private GoogleBooksReadingModes readingModes;
    private Integer pageCount;
    private String printType;
    private List<String> categories;
    private String maturityRating;
    private Boolean allowAnonLogging;
    private String contentVersion;
    private GoogleBookSpanelizationSummary panelizationSummary;
    private GoogleBooksImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;

    public GoogleBooksVolumeInfo(){}

    public GoogleBooksVolumeInfo(String title, List<String> authors, String publisher, String publishedDate, String description,
                                 List<GoogleBooksIndustryIdentifier> industryIdentifiers, GoogleBooksReadingModes readingModes,
                                 Integer pageCount, String printType, List<String> categories, String maturityRating,
                                 Boolean allowAnonLogging, String contentVersion, GoogleBookSpanelizationSummary panelizationSummary,
                                 GoogleBooksImageLinks imageLinks, String language, String previewLink, String infoLink,
                                 String canonicalVolumeLink) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.industryIdentifiers = industryIdentifiers;
        this.readingModes = readingModes;
        this.pageCount = pageCount;
        this.printType = printType;
        this.categories = categories;
        this.maturityRating = maturityRating;
        this.allowAnonLogging = allowAnonLogging;
        this.contentVersion = contentVersion;
        this.panelizationSummary = panelizationSummary;
        this.imageLinks = imageLinks;
        this.language = language;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GoogleBooksIndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<GoogleBooksIndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public GoogleBooksReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(GoogleBooksReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public GoogleBookSpanelizationSummary getPanelizationSummary() {
        return panelizationSummary;
    }

    public void setPanelizationSummary(GoogleBookSpanelizationSummary panelizationSummary) {
        this.panelizationSummary = panelizationSummary;
    }

    public GoogleBooksImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(GoogleBooksImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }
}
