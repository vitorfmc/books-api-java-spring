package com.vitor.cordeiro.teste.quarkus.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookResponseDto {

    public class SearchInfo {
        private Integer limit;
        private Integer offset;

        public SearchInfo(Integer limit, Integer offset){
            this.limit = limit;
            this.offset = offset;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }
    }

    private SearchInfo searchInfo;
    private List<Book> data;
    private List<String> errors;
    private String requestId;

    public BookResponseDto(Integer limit, Integer offset, List<Book> data, List<String> errors) {
        this.searchInfo = new SearchInfo(limit, offset);
        this.data = data != null && !data.isEmpty() ? data : new ArrayList<>();
        this.errors = errors;
        this.requestId = UUID.randomUUID().toString();
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
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
