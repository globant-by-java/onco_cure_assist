package com.globant.internal.oncocureassist.service.validator

import com.globant.internal.oncocureassist.domain.dictionary.AgeClass
import com.globant.internal.oncocureassist.repository.entity.Patient
import com.globant.internal.oncocureassist.util.SampleDataProvider
import org.springframework.context.MessageSource
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validator
import java.time.LocalDate

import static org.springframework.context.i18n.LocaleContextHolder.getLocale

class PatientValidatorTest extends Specification {

    def jsrValidator = Mock(Validator)
    def messageSource = Mock(MessageSource)
    def treatmentValidator = Mock(ConstraintValidator)
    def diagnosticsValidator = Mock(ConstraintValidator)
    def geneticPredictorsValidator = Mock(ConstraintValidator)
    def validator = new PatientValidator(jsrValidator, messageSource, treatmentValidator, diagnosticsValidator, geneticPredictorsValidator)


    def 'verify that we do not validate treatment, diagnostics, geneticPredictors if they are null'() {
        given:
            def patient = SampleDataProvider.createPatient()
            patient.treatment = treatment
            patient.diagnostics = diagnostics
            patient.geneticPredictors = geneticPredictors
            patient = patient as Patient

        when:
            def errors = validator.validate(patient)

        then:
            1 * jsrValidator.validate(patient) >> []
            callCount * treatmentValidator.validate(patient.treatment) >> []
            callCount * diagnosticsValidator.validate(patient.diagnostics) >> []
            callCount * geneticPredictorsValidator.validate(patient.geneticPredictors) >> []
            0 * _
            noExceptionThrown()
            errors.isEmpty()

        where:
            treatment | diagnostics | geneticPredictors || callCount
            [:]       | [:]         | [:]               || 1
            null      | null        | null              || 0
    }


    @Unroll
    def "validate patient when #fieldName = #fieldValue"() {
        given:
            def patient = SampleDataProvider.createPatient() as Patient
            patient[fieldName] = fieldValue
            patient.treatment = null
            patient.diagnostics = null
            patient.geneticPredictors = null

        when:
            def errors = validator.validate(patient)

        then:
            1 * jsrValidator.validate(patient) >> []
            errorMessageCount * messageSource.getMessage(errorTemplate, _, getLocale()) >> 'error'

            noExceptionThrown()
            errors.size() == errorsSize
            if (errorMessageCount == 1) {
                assert errors.find {
                    it.code == null &&
                            it.object == 'Patient' &&
                            it.field == fieldName &&
                            it.description == 'error'
                }
            }

        where:
            fieldValue                  | fieldName     | errorTemplate                                   || errorMessageCount || errorsSize
            1                           | 'gender'      | 'patient.validation.gender.invalid.value.error' || 0                 || 0
            0                           | 'gender'      | 'patient.validation.gender.invalid.value.error' || 0                 || 0
            null                        | 'gender'      | 'patient.validation.gender.invalid.value.error' || 1                 || 1
            2                           | 'gender'      | 'patient.validation.gender.invalid.value.error' || 1                 || 1
            -1                          | 'gender'      | 'patient.validation.gender.invalid.value.error' || 1                 || 1

            LocalDate.now()             | 'birthDate'   | 'validation.invalid.date.range.error'           || 0                 || 2
            LocalDate.of(1990, 1, 1)    | 'birthDate'   | 'validation.invalid.date.range.error'           || 0                 || 1
            LocalDate.of(1989, 12, 31)  | 'birthDate'   | 'validation.invalid.date.range.error'           || 1                 || 2
            LocalDate.now().plusDays(1) | 'birthDate'   | 'validation.invalid.date.range.error'           || 1                 || 3
            null                        | 'birthDate'   | 'validation.invalid.date.range.error'           || 1                 || 1

            LocalDate.now()             | 'contactDate' | 'validation.invalid.date.range.error'           || 0                 || 0
            LocalDate.of(1990, 1, 1)    | 'contactDate' | 'validation.invalid.date.range.error'           || 0                 || 2
            LocalDate.of(1989, 12, 31)  | 'contactDate' | 'validation.invalid.date.range.error'           || 1                 || 3
            LocalDate.now().plusDays(1) | 'contactDate' | 'validation.invalid.date.range.error'           || 1                 || 1
            null                        | 'contactDate' | 'validation.invalid.date.range.error'           || 1                 || 1
    }


    def 'validate patient age'() {
        given:
            def patient = SampleDataProvider.createPatient() as Patient
            patient.age = age
            patient.birthDate = birthDate
            patient.contactDate = contactDate
            def ageClass = AgeClass.getByAge(age)
            patient.ageClass = ageClass ? ageClass.code as Integer : 2
            patient.treatment = null
            patient.diagnostics = null
            patient.geneticPredictors = null

        when:
            def errors = validator.validate(patient)

        then:
            1 * jsrValidator.validate(patient) >> []
            errorMessageCount * messageSource.getMessage('patient.validation.invalid.age.error', _, getLocale()) >> 'error'

            if (errorMessageCount == 1) {
                assert errors.find {
                    it.code == null &&
                            it.object == 'Patient' &&
                            it.field == 'age' &&
                            it.description == 'error'
                }
            }

        where:
            birthDate                      | contactDate     | age  || errorMessageCount
            LocalDate.now().minusYears(20) | LocalDate.now() | 20   || 0
            LocalDate.now().minusYears(20) | LocalDate.now() | 10   || 1
            LocalDate.now().minusYears(20) | LocalDate.now() | null || 1
    }


    def 'validate patient ageClass'() {
        given:
            def patient = SampleDataProvider.createPatient() as Patient
            patient.age = age
            patient.ageClass = ageClass
            patient.treatment = null
            patient.diagnostics = null
            patient.geneticPredictors = null

        when:
            def errors = validator.validate(patient)

        then:
            1 * jsrValidator.validate(patient) >> []
            errorMessageCount * messageSource.getMessage('patient.validation.invalid.age.class.error', _, getLocale()) >> 'error'

            if (errorMessageCount == 1) {
                assert errors.find {
                    it.code == null &&
                            it.object == 'Patient' &&
                            it.field == 'ageClass' &&
                            it.description == 'error'
                }
            }

        where:
            ageClass | age  || errorMessageCount
            0        | 0    || 0
            0        | 9    || 0
            1        | 10   || 0
            1        | 19   || 0
            2        | 20   || 0
            2        | 29   || 0
            3        | 30   || 0
            3        | 39   || 0
            4        | 40   || 0
            4        | 49   || 0
            5        | 50   || 0
            5        | 59   || 0
            6        | 60   || 0
            6        | 69   || 0
            7        | 70   || 0
            7        | 79   || 0
            8        | 80   || 0
            8        | 101  || 0
            8        | null || 1
            null     | 10   || 1
            null     | null || 1
    }


    def 'validate that birthDate must be before contactDate'() {
        given:
            def patient = SampleDataProvider.createPatient() as Patient
            patient.birthDate = birthDate
            patient.contactDate = contactDate
            patient.treatment = null
            patient.diagnostics = null
            patient.geneticPredictors = null

        when:
            def errors = validator.validate(patient)

        then:
            1 * jsrValidator.validate(patient) >> []

            errorMessageCount * messageSource.getMessage('patient.validation.birth.date.greater.than.contact.date.error', [], getLocale()) >> 'error'

            if (errorMessageCount == 1) {
                assert errors.find {
                    it.code == null &&
                            it.object == 'Patient' &&
                            it.field == 'birthDate' &&
                            it.description == 'error'
                }
            }

        where:
            birthDate                     | contactDate                   || errorMessageCount
            LocalDate.now()               | LocalDate.now()               || 1
            LocalDate.now().minusYears(1) | LocalDate.now().minusYears(2) || 1
    }
}
