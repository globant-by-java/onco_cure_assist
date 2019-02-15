package com.globant.internal.oncocureassist.service.validator;

import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import com.globant.internal.oncocureassist.repository.entity.Treatment;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Validator;


@Component
class TreatmentValidator extends AbstractConstraintValidator<Treatment> {


    TreatmentValidator(Validator jsrValidator, MessageSource messageSource) {
        super(jsrValidator, messageSource, Treatment.class.getSimpleName());
    }


    @Override
    public List<ConstraintError> validate(Treatment treatment) {
        List<ConstraintError> errors = new ArrayList<>(getJsrErrors(treatment));

        LocalDate surgeryDate = treatment.getSurgeryDate();
        if (!isDateRangeValid(surgeryDate)) {
            errors.add(createError("surgeryDate", "validation.invalid.date.range.error", getMinDate(), getMaxDate()));
        }

        return errors;
    }
}
