package com.globant.internal.oncocureassist.domain.exception;

import java.util.List;

public class PatientValidationException extends RuntimeException {


    private final List<ConstraintError> errors;


    public PatientValidationException(List<ConstraintError> errors) {
        this.errors = errors;
    }


    public List<ConstraintError> getErrors() {
        return errors;
    }
}
