package com.vitor.cordeiro.teste.quarkus.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RegisterForReflection
public class BookResponseDto {

    private SearchInfoDTO searchInfo;
    private List<Book> data;
    private List<String> errors;
    private String requestId;

    public BookResponseDto(Integer limit, Integer offset, List<Book> data, List<String> errors) {
        this.searchInfo = new SearchInfoDTO(limit, offset);
        this.data = data != null && !data.isEmpty() ? data : new ArrayList<>();
        this.errors = errors;
        this.requestId = UUID.randomUUID().toString();
    }

    public SearchInfoDTO getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfoDTO searchInfo) {
        this.searchInfo = searchInfo;
    }

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
