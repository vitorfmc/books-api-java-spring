package com.rovitapps.google.books.integration.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDTO {

    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Library Code is mandatory")
    private String libraryCode;

    @NotNull(message = "Cataloging Date is mandatory")
    private String catalogingDate;

}
