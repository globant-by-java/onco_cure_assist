package com.globant.internal.oncocureassist.endpoint;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

import com.globant.internal.oncocureassist.domain.exception.ClassifierCreationException;
import com.globant.internal.oncocureassist.domain.exception.ClassifierExecutionException;
import com.globant.internal.oncocureassist.domain.exception.PatientValidationException;
import org.hibernate.StaleObjectStateException;
import org.springframework.context.MessageSource;
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

    private final MessageSource messageSource;


    public ExceptionTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


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


    @ExceptionHandler
    public ResponseEntity<Object> handleStaleObjectStateException(StaleObjectStateException exc, WebRequest request) {
        ApiError error = new ApiError(messageSource.getMessage("patient.optimistic.lock.error", null, getLocale()));
        return handleExceptionInternal(exc, error, null, HttpStatus.CONFLICT, request);
    }


    @ExceptionHandler
    public ResponseEntity<Object> handleClassifierCreationException(ClassifierCreationException exc, WebRequest request) {
        ApiError error = new ApiError(messageSource.getMessage("classifier.creation.error", null, getLocale()));
        return handleExceptionInternal(exc, error, null, HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler
    public ResponseEntity<Object> handleClassifierExecutionException(ClassifierExecutionException exc, WebRequest request) {
        ApiError error = new ApiError(messageSource.getMessage("classifier.execution.error", null, getLocale()));
        return handleExceptionInternal(exc, error, null, HttpStatus.BAD_REQUEST, request);
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