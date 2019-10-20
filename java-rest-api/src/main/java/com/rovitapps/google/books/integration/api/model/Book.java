package com.rovitapps.google.books.integration.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String Id;

    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Library Code is mandatory")
    private String libraryCode;

    @NotNull(message = "Cataloging Date is mandatory")
    private String catalogingDate;

    private List<String> authors;

    //@DBRef
    private List<Image> images;

    @NotNull(message = "Create Date is mandatory")
    private Date createDate;

    @NotNull(message = "Update Date is mandatory")
    private Date updateDate;

    public Book(String title) {
        setTitle(title);
        setAuthors(new ArrayList<>());
        setCreateDate(new Date());
        setUpdateDate(new Date());
        setImages(new ArrayList<>());
    }
}
