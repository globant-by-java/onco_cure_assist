package com.globant.internal.oncocureassist.endpoint;

import com.globant.internal.oncocureassist.domain.exception.PatientValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ PatientValidationException.class })
    public ResponseEntity<Object> handlePatientValidationException(PatientValidationException exc, WebRequest request) {
        if (CollectionUtils.isEmpty(exc.getErrors())) {
            ApiError error = new ApiError(exc.getMessage());
            return handleExceptionInternal(exc, error, null, HttpStatus.BAD_REQUEST, request);
        }

        List<ApiError> errors = exc.getErrors()
                .stream()
                .map(e -> new ApiError(e.getObject(), e.getField(), e.getCode(), e.getDescription()))
                .collect(Collectors.toList());

        return handleExceptionInternal(exc, errors, null, HttpStatus.BAD_REQUEST, request);
    }


    private static class ApiError {

        private final String object;
        private final String field;
        private final String code;
        private final String description;


        ApiError(String description) {
            this(null, null, null, description);
        }


        ApiError(String object, String field, String code, String description) {
            this.code = code;
            this.description = description;
            this.object = object;
            this.field = field;
        }


        public String getCode() {
            return code;
        }


        public String getDescription() {
            return description;
        }


        public String getField() {
            return field;
        }


        public String getObject() {
            return object;
        }
    }
}