package com.neueda.muskaan1.validation;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private final String message;
    private final List<FieldError> errors;

    public ErrorResponse(String message) {
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public void addValidationError(String field, String message) {
        FieldError error = new FieldError(field, message);
        errors.add(error);
    }

    public String getMessage() {
        return message;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public record FieldError(String field, String message) {
    }
}

