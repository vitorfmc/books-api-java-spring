package com.rovitapps.google.books.integration.api.exception;

public class ResponseException extends Exception {

    private Object data;

    public ResponseException(String msg, Object data){
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
