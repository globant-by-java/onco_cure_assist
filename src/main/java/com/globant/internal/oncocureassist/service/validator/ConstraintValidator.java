package com.globant.internal.oncocureassist.service.validator;

import com.globant.internal.oncocureassist.domain.exception.ConstraintError;

import java.util.List;

public interface ConstraintValidator<T> {

    List<ConstraintError> validate(T object);
}
