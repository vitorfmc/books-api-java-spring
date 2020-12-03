package com.vitor.cordeiro.teste.quarkus.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class SearchInfoDTO {
    private Integer limit;
    private Integer offset;

    public SearchInfoDTO(){}

    public SearchInfoDTO(Integer limit, Integer offset){
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