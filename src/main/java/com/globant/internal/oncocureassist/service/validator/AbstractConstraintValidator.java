package com.globant.internal.oncocureassist.service.validator;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;

abstract class AbstractConstraintValidator<T> implements ConstraintValidator<T> {

    private final javax.validation.Validator jsrValidator;
    private final MessageSource messageSource;
    private final String className;


    AbstractConstraintValidator(javax.validation.Validator jsrValidator, MessageSource messageSource, String className) {
        this.jsrValidator = jsrValidator;
        this.messageSource = messageSource;
        this.className = className;
    }


    List<ConstraintError> getJsrErrors(T object) {
        Set<ConstraintViolation<T>> jsrErrors = jsrValidator.validate(object);

        return jsrErrors.stream()
                .map(ConstraintError::new)
                .collect(Collectors.toList());
    }


    boolean isDateRangeValid(LocalDate date) {
        LocalDate minDate = getMinDate();
        LocalDate maxDate = getMaxDate();
        return date == null
                || ((date.isAfter(minDate) || date.equals(minDate)) && (date.isBefore(maxDate) || date.equals(maxDate)));
    }


    ConstraintError createError(String fieldName, String template, Object... objects) {
        String msg = messageSource.getMessage(template, objects, getLocale());
        return new ConstraintError(className, fieldName, null, msg);
    }


    LocalDate getMinDate() {
        return LocalDate.of(1900, 1, 1);
    }


    LocalDate getMaxDate() {
        return LocalDate.now();
    }
}
