package com.globant.internal.oncocureassist.service.validator;

import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import com.globant.internal.oncocureassist.repository.entity.Diagnostics;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.validation.Validator;

@Component
class DiagnosticsValidator extends AbstractConstraintValidator<Diagnostics> {


    DiagnosticsValidator(Validator jsrValidator, MessageSource messageSource) {
        super(jsrValidator, messageSource, Diagnostics.class.getSimpleName());
    }


    @Override
    public List<ConstraintError> validate(Diagnostics diagnostics) {
        return getJsrErrors(diagnostics);
    }
}
