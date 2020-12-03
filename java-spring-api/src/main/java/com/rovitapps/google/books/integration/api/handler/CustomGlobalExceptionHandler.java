package com.rovitapps.google.books.integration.api.handler;

import com.google.gson.JsonObject;
import com.rovitapps.google.books.integration.api.exception.DataNotFoundException;
import com.rovitapps.google.books.integration.api.exception.DataValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ResponseEntityExceptionHandler.class);

    @Autowired
    protected MessageSource messageSource;

    // Handler pro @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("errors", errors);

        return ResponseEntity
                    .status(status)
                    .body(body);
    }

    @ExceptionHandler( {DataNotFoundException.class } )
    public ResponseEntity<Object> dataNotFoundException(DataValidationException ex, WebRequest request) {
        return errorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( { DataValidationException.class } )
    public ResponseEntity<Object> dataValidationException(DataValidationException ex, WebRequest request) {
        return errorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> errorResponse(DataValidationException ex, HttpStatus status){
        List<String> errors = new ArrayList<>();
        try{
            if(ex.getData() == null){
                errors.add(ex.getMessage());
            }if(ex.getData() instanceof  String){
                errors.add((String) ex.getData());
            }else{
                errors.addAll((List<String>) ex.getData());
            }
        }catch (Exception e){
            LOGGER.error("[USER.SAVE] Error: " + e.getMessage(), e);
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("errors", errors);

        return ResponseEntity
                .status(status)
                .body(body);
    }

    @ExceptionHandler({ Exception.class } )
    public ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) {
        LOGGER.error("[USER.SAVE] Error: " + e.getMessage(), e);
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                messageSource.getMessage("application.generic.error", null,
                        null, Locale.getDefault()));
    }

    private ResponseEntity errorResponse(HttpStatus status, String message){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message",message);

        return ResponseEntity
                .status(status)
                .body(jsonObject.toString());
    }

}