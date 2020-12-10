package com.rovitapps.google.books.integration.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDTO {

    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Library Code is mandatory")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Library Code must be only letters and numbers")
    private String libraryCode;

    @NotNull(message = "Cataloging Date is mandatory")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$",
            message="The date format should be dd/mm/yyyy")
    private String catalogingDate;

    public void setTitle(String title) {
        if(title != null)
            title = title.trim();

        this.title = title;
    }
}
