package com.rovitapps.google.books.integration.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rovitapps.google.books.integration.api.util.Utils;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Book {

    @Id
    private String id;

    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Library Code is mandatory")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Library Code must be only letters and numbers")
    private String libraryCode;

    @JsonFormat(pattern="dd/MM/yyyy")
    @NotNull(message = "Cataloging Date is mandatory")
    private Date catalogingDate;

    @JsonIgnore
    @NotNull(message = "Create Date is mandatory")
    private Date createDate;

    @JsonIgnore
    @NotNull(message = "Update Date is mandatory")
    private Date updateDate;

    private String googleBookId;

    private List<String> authors;

    //@DBRef
    private List<Image> images;

    public Book(@NotNull String libraryCode,
                @NotNull String title,
                @NotNull Date catalogingDate) {

        if(title != null)
            setTitle(title.trim());

        setAuthors(new ArrayList<>());
        setCreateDate(Utils.getDateNow());
        setUpdateDate(Utils.getDateNow());
        setLibraryCode(libraryCode.toUpperCase().trim());
        setCatalogingDate(catalogingDate);
        setImages(new ArrayList<>());
    }
}
