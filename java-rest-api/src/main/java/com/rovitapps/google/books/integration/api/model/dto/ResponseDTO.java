package com.rovitapps.google.books.integration.api.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    int offset;
    int limit;
    long totalElements;
    Object content;

}
