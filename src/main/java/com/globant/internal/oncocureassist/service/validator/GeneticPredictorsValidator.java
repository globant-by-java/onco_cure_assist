package com.globant.internal.oncocureassist.service.validator;

import com.globant.internal.oncocureassist.domain.dictionary.AcetylationType;
import com.globant.internal.oncocureassist.domain.dictionary.Genotype;
import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import com.globant.internal.oncocureassist.repository.entity.GeneticPredictors;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Validator;


@Component
class GeneticPredictorsValidator extends AbstractConstraintValidator<GeneticPredictors> {

    private final Map<String, List<String>> genotypeValidationRules;
    private final Field[] geneticPredictorsFields;


    GeneticPredictorsValidator(Validator jsrValidator,
                               MessageSource messageSource) {
        super(jsrValidator, messageSource, GeneticPredictors.class.getSimpleName());
        this.genotypeValidationRules = Arrays.stream(Genotype.values())
                .collect(Collectors.toMap(Genotype::getCode, Genotype::getValues));
        this.geneticPredictorsFields = GeneticPredictors.class.getDeclaredFields();
    }


    @Override
    public List<ConstraintError> validate(GeneticPredictors geneticPredictors) {
        List<ConstraintError> errors = new ArrayList<>();

        String defaultErrorMessage = "genetic.predictions.field.validation.error";
        for (Field field : geneticPredictorsFields) {
            String fieldName = field.getName();
            field.setAccessible(true);
            List<String> expectedValues = genotypeValidationRules.getOrDefault(fieldName, Collections.emptyList());

            if (expectedValues.isEmpty()) {
                continue;
            }

            String value = getValueOrThrow(geneticPredictors, field);
            if (value != null && !expectedValues.contains(value)) {
                errors.add(createError(fieldName, defaultErrorMessage, expectedValues));
            }
        }

        String acetylationTypeCode = geneticPredictors.getAcetylationType();
        AcetylationType acetylationType = AcetylationType.getByCode(acetylationTypeCode);
        if (acetylationType == null && acetylationTypeCode != null) {
            errors.add(createError("acetylationType", defaultErrorMessage, AcetylationType.getAllCodes()));
        }


        return errors;
    }


    private String getValueOrThrow(GeneticPredictors geneticPredictors, Field field) {
        try {
            return (String) field.get(geneticPredictors);
        } catch (IllegalAccessException exc) {
            throw new RuntimeException("Cannot read GeneticPredictors field " + field.getName(), exc);
        }
    }
}
