package com.rovitapps.google.books.integration.api.exception;

public class DataNotFoundException extends Exception {

    private Object data;

    public DataNotFoundException(Object data){
        setData(data);
    }

    public DataNotFoundException(String msg, Object data){
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
