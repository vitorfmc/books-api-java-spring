package com.vitor.cordeiro.teste.quarkus.exception;

public class InvalidCredentialResponseStatusException extends RuntimeException {
    public InvalidCredentialResponseStatusException(String message) {
        super(message);
    }

    public InvalidCredentialResponseStatusException() {
    }

    public InvalidCredentialResponseStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialResponseStatusException(Throwable cause) {
        super(cause);
    }
}
