package com.globant.internal.oncocureassist.endpoint;

import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ TransactionSystemException.class })
    public ResponseEntity<Object> handleTransactionSystemException(Exception ex, WebRequest request) throws Exception {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException error = (ConstraintViolationException) cause;
            return handleConstraintViolationException(error, request);
        }

        return handleException(ex, request);
    }


    private ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exc,
                                                                      WebRequest request) {
        if (CollectionUtils.isEmpty(exc.getConstraintViolations())) {
            ApiError error = new ApiError(exc.getMessage());
            return handleExceptionInternal(exc, error, null, HttpStatus.BAD_REQUEST, request);
        }

        List<ApiError> errors = exc.getConstraintViolations()
                .stream()
                .map(ApiError::new)
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


        ApiError(ConstraintViolation<?> cv) {
            this.object = Optional.ofNullable(cv.getLeafBean()).map(o -> o.getClass().getSimpleName()).orElse(null);

            this.field = Optional.ofNullable(cv.getPropertyPath())
                    .filter(nodes -> nodes instanceof PathImpl)
                    .map(nodes -> ((PathImpl) nodes).getLeafNode())
                    .map(NodeImpl::getName)
                    .orElse(null);

            this.code = Optional.ofNullable(cv.getConstraintDescriptor())
                    .map(descriptor -> descriptor.getAnnotation().annotationType().getSimpleName())
                    .orElse(null);

            this.description = cv.getMessage();
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