package com.neueda.muskaan1.exception;

public class InvalidDataTypeException extends RuntimeException {

    public InvalidDataTypeException(String fieldName, String value) {
        super("Invalid data type for field '" + fieldName + "': " + value);
    }
}
