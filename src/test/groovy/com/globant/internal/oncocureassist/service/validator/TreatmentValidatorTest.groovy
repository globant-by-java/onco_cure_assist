package com.globant.internal.oncocureassist.service.validator

import com.globant.internal.oncocureassist.domain.dictionary.ValidationType
import com.globant.internal.oncocureassist.repository.entity.Treatment
import com.globant.internal.oncocureassist.util.SampleDataProvider
import org.springframework.context.MessageSource
import spock.lang.Specification

import javax.validation.Validator
import java.time.LocalDate

import static org.springframework.context.i18n.LocaleContextHolder.getLocale

class TreatmentValidatorTest extends Specification {

    def jsrValidator = Mock(Validator)
    def messageSource = Mock(MessageSource)
    def validator = new TreatmentValidator(jsrValidator, messageSource)


    def 'verify that surgery date can be null'() {
        given:
            def treatment = SampleDataProvider.createPatient().treatment as Treatment
            treatment.surgeryDate = null

        when:
            def errors = validator.validate(treatment, ValidationType.CREATE)

        then:
            1 * jsrValidator.validate(treatment) >> []
            0 * _
            noExceptionThrown()
            errors.isEmpty()
    }


    def 'verify that surgery date must be validated if it is not null'() {
        given:
            def treatment = SampleDataProvider.createPatient().treatment as Treatment
            treatment.surgeryDate = surgeryDate

        when:
            def errors = validator.validate(treatment, ValidationType.CREATE)

        then:
            1 * jsrValidator.validate(treatment) >> []
            errorMessageCount * messageSource.getMessage('validation.invalid.date.range.error', [LocalDate.of(1990, 1, 1), LocalDate.now()], getLocale()) >> 'error'
            0 * _
            noExceptionThrown()
            errors.size() == errorMessageCount

        where:
            surgeryDate                 || errorMessageCount
            LocalDate.of(1990, 1, 1)    || 0
            LocalDate.now()             || 0
            LocalDate.of(1989, 12, 31)  || 1
            LocalDate.now().plusDays(1) || 1
    }


    def 'verify error response'() {
        given:
            def treatment = SampleDataProvider.createPatient().treatment as Treatment
            treatment.surgeryDate = LocalDate.now().plusDays(1)

        when:
            def errors = validator.validate(treatment, ValidationType.CREATE)

        then:
            1 * jsrValidator.validate(treatment) >> []
            1 * messageSource.getMessage('validation.invalid.date.range.error', [LocalDate.of(1990, 1, 1), LocalDate.now()], getLocale()) >> 'error'
            0 * _
            noExceptionThrown()
            errors.size() == 1
            !errors[0].code
            errors[0].object == 'Treatment'
            errors[0].field == 'surgeryDate'
            errors[0].description == 'error'
    }
}
