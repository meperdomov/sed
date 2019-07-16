package co.com.simac.sed.util;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ArrayList<String> errors = new ArrayList<>();

    private final String errorMessage;

    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}