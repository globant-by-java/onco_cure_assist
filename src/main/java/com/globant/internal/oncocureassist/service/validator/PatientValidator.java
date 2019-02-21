package com.globant.internal.oncocureassist.service.validator;

import com.globant.internal.oncocureassist.domain.dictionary.AgeClass;
import com.globant.internal.oncocureassist.domain.dictionary.Gender;
import com.globant.internal.oncocureassist.domain.dictionary.ValidationType;
import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import com.globant.internal.oncocureassist.repository.PatientRepository;
import com.globant.internal.oncocureassist.repository.entity.Diagnostics;
import com.globant.internal.oncocureassist.repository.entity.GeneticPredictors;
import com.globant.internal.oncocureassist.repository.entity.Patient;
import com.globant.internal.oncocureassist.repository.entity.Treatment;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Validator;

@Component
class PatientValidator extends AbstractConstraintValidator<Patient> {

    private final ConstraintValidator<Treatment> treatmentValidator;
    private final ConstraintValidator<Diagnostics> diagnosticsValidator;
    private final ConstraintValidator<GeneticPredictors> geneticPredictorsValidator;
    private final PatientRepository patientRepository;


    PatientValidator(Validator jsrValidator, MessageSource messageSource,
                     ConstraintValidator<Treatment> treatmentValidator,
                     ConstraintValidator<Diagnostics> diagnosticsValidator,
                     ConstraintValidator<GeneticPredictors> geneticPredictorsValidator,
                     PatientRepository patientRepository) {
        super(jsrValidator, messageSource, Patient.class.getSimpleName());
        this.treatmentValidator = treatmentValidator;
        this.diagnosticsValidator = diagnosticsValidator;
        this.geneticPredictorsValidator = geneticPredictorsValidator;
        this.patientRepository = patientRepository;
    }


    @Override
    public List<ConstraintError> validate(Patient patient, ValidationType validationType) {
        List<ConstraintError> errors = new ArrayList<>();
        validatePatient(patient, validationType, errors);
        validateTreatment(patient.getTreatment(), validationType, errors);
        validateDiagnostics(patient.getDiagnostics(), validationType, errors);
        validateGeneticPredictors(patient.getGeneticPredictors(), validationType, errors);

        return errors;
    }


    private void validatePatient(Patient patient, ValidationType validationType, List<ConstraintError> errors) {
        errors.addAll(getJsrErrors(patient));

        if (validationType == ValidationType.CREATE) {
            Optional.ofNullable(patient.getCardNumber())
                    .map(patientRepository::findByCardNumber)
                    .ifPresent(p -> {
                        String cardNumber = p.getCardNumber();
                        errors.add(createError("cardNumber", "patient.validation.card.number.not.unique.error", cardNumber));
                    });
        }

        Gender gender = Gender.of(patient.getGender());
        if (gender == null) {
            errors.add(createError("gender", "patient.validation.gender.invalid.value.error"));
        }

        LocalDate birthDate = patient.getBirthDate();
        if (birthDate == null || !isDateRangeValid(birthDate)) {
            errors.add(createError("birthDate", "validation.invalid.date.range.error", getMinDate(), getMaxDate()));
        }

        LocalDate contactDate = patient.getContactDate();
        if (contactDate == null || !isDateRangeValid(contactDate)) {
            errors.add(createError("contactDate", "validation.invalid.date.range.error", getMinDate(), getMaxDate()));
        }

        if (contactDate != null && birthDate != null) {
            if (birthDate.isAfter(contactDate) || birthDate.equals(contactDate)) {
                errors.add(createError("birthDate", "patient.validation.birth.date.greater.than.contact.date.error"));
            }

            int expectedAge = Period.between(birthDate, contactDate).getYears();
            if (patient.getAge() == null || patient.getAge() != expectedAge) {
                errors.add(createError("age", "patient.validation.invalid.age.error", expectedAge));
            }
        }

        AgeClass expectedAgeClass = AgeClass.getByAge(patient.getAge());
        Integer actualAgeClass = patient.getAgeClass();
        if (actualAgeClass == null
                || expectedAgeClass == null
                || !String.valueOf(actualAgeClass).equals(expectedAgeClass.getCode())) {
            errors.add(createError("ageClass", "patient.validation.invalid.age.class.error"));
        }
    }


    private void validateTreatment(Treatment treatment, ValidationType validationType, List<ConstraintError> errors) {
        Optional.ofNullable(treatment)
                .ifPresent(tr -> {
                    List<ConstraintError> treatmentErrors = treatmentValidator.validate(tr, validationType);
                    errors.addAll(treatmentErrors);
                });
    }


    private void validateDiagnostics(Diagnostics diagnostics, ValidationType validationType, List<ConstraintError> errors) {
        Optional.ofNullable(diagnostics)
                .ifPresent(ds -> {
                    List<ConstraintError> diagnosticsErrors = diagnosticsValidator.validate(ds, validationType);
                    errors.addAll(diagnosticsErrors);
                });
    }


    private void validateGeneticPredictors(GeneticPredictors geneticPredictors, ValidationType validationType, List<ConstraintError> errors) {
        Optional.ofNullable(geneticPredictors)
                .ifPresent(gp -> {
                    List<ConstraintError> geneticPredictorsErrors = geneticPredictorsValidator.validate(gp, validationType);
                    errors.addAll(geneticPredictorsErrors);
                });
    }
}
