package com.lukas.TcgTracker.exception;

public class NoSuchBulkTypeException extends RuntimeException {
    public NoSuchBulkTypeException(String type) {
        super(String.format("There is no such a type as: %s", type));
    }
}
