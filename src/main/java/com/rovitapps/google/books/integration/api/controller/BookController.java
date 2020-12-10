package com.rovitapps.google.books.integration.api.controller;

import com.rovitapps.google.books.integration.api.exception.DataNotFoundException;
import com.rovitapps.google.books.integration.api.exception.DataValidationException;
import com.rovitapps.google.books.integration.api.model.Book;
import com.rovitapps.google.books.integration.api.model.dto.BookCreateDTO;
import com.rovitapps.google.books.integration.api.model.dto.ResponseDTO;
import com.rovitapps.google.books.integration.api.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/v1/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private BookService service;

    @ApiOperation(value="Create a new Book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 504, message = "Method not allowed. The URL is incorrect.")
    })
    @PostMapping
    public ResponseEntity create (@Valid @RequestBody BookCreateDTO dto)
            throws DataValidationException, DataNotFoundException {

        return getResponse(service.save(dto), HttpStatus.CREATED);
    }

    @ApiOperation(value="Update a Book by ID", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 504, message = "Method not allowed. The URL is incorrect.")
    })
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable("id") String id, @Valid @RequestBody BookCreateDTO dto)
            throws DataValidationException, DataNotFoundException {

        return getResponse(service.update(id, dto), HttpStatus.OK);
    }

    @ApiOperation(value="Remove a Book")
    @ApiResponses(value = {
            @ApiResponse(code = 504, message = "Method not allowed. The URL is incorrect.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") String id)
            throws DataValidationException, DataNotFoundException {

        service.delete(id);
        return getResponse(null, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="List Books", response = Book.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 504, message = "Method not allowed. The URL is incorrect.")
    })
    @GetMapping
    public ResponseEntity list (@RequestParam(value = "limit", defaultValue = "10") int limit,
                                @RequestParam(value = "offset", defaultValue = "0") int offset,
                                @RequestParam(value = "q", required = false) String q)
            throws DataValidationException {

        Page<Book> response = service.findAll(limit, offset, q);

        return getResponse(
                new ResponseDTO(offset, limit, response.getTotalElements(), response.getContent()),
                HttpStatus.ACCEPTED
        );
    }

    private ResponseEntity getResponse(Object body, HttpStatus status){
        return ResponseEntity.status(status).body(body);
    }

}
