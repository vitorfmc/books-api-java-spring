package com.rovitapps.google.books.integration.api.exception;

public class DataValidationException extends Exception {

    private Object data;

    public DataValidationException(Object data){
        setData(data);
    }

    public DataValidationException(String msg, Object data){
        super(msg);
        setData(data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
