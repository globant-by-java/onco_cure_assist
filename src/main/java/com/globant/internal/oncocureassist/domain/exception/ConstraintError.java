package com.globant.internal.oncocureassist.domain.exception;

import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;

import java.util.Optional;
import javax.validation.ConstraintViolation;

public class ConstraintError {

    private final String object;
    private final String field;
    private final String code;
    private final String description;


    public ConstraintError(String object, String field, String code, String description) {
        this.object = object;
        this.field = field;
        this.code = code;
        this.description = description;
    }


    public ConstraintError(ConstraintViolation<?> cv) {
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


    public String getObject() {
        return object;
    }


    public String getField() {
        return field;
    }


    public String getCode() {
        return code;
    }


    public String getDescription() {
        return description;
    }
}
