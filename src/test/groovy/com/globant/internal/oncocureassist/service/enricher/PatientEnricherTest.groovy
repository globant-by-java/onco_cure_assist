package com.globant.internal.oncocureassist.service.enricher

import com.globant.internal.oncocureassist.repository.entity.Patient
import com.globant.internal.oncocureassist.util.SampleDataProvider
import spock.lang.Specification

import java.time.LocalDate

class PatientEnricherTest extends Specification {

    def enricher = new PatientEnricher()


    def 'verify that we do not calculate survivalMonth and classId if treatment is null'() {
        given:
            def patient = SampleDataProvider.createPatient(survivalMonth: null, classId: null)
            patient.treatment = null

        when:
            enricher.enrich(patient as Patient)

        then:
            noExceptionThrown()
            patient.survivalMonth == null
            patient.classId == null
    }


    def 'verify that we calculate survivalMonth and classId if treatment is not null'() {
        given:
            def patientMap = SampleDataProvider.createPatient(contactDate: contactDate, survivalMonth: null, classId: null)
            patientMap.treatment << [surgeryDate: surgeryDate]
            def patient = patientMap as Patient

        when:
            enricher.enrich(patient)

        then:
            noExceptionThrown()
            patient.survivalMonth == survivalMonth
            patient.classId == classId

        where:
            contactDate                     | surgeryDate     || survivalMonth || classId
            LocalDate.now().minusDays(1)    | LocalDate.now() || 0             || 0
            LocalDate.now().minusMonths(1)  | LocalDate.now() || 1             || 0
            LocalDate.now().minusMonths(35) | LocalDate.now() || 35            || 0
            LocalDate.now().minusMonths(36) | LocalDate.now() || 36            || 1
            LocalDate.now().minusMonths(37) | LocalDate.now() || 37            || 1
    }


    def 'verify that we do not have error if diagnostics is null'() {
        given:
            def patientMap = SampleDataProvider.createPatient()
            patientMap.diagnostics = null
            def patient = patientMap as Patient

        when:
            enricher.enrich(patient as Patient)

        then:
            noExceptionThrown()
            patient.diagnostics == null
    }


    def 'verify that we calculate t,n,m based on tnm field'() {
        given:
            def patientMap = SampleDataProvider.createPatient()
            patientMap.diagnostics << [tnm: tnm, t: null, n: null, m: null]
            def patient = patientMap as Patient

        when:
            enricher.enrich(patient as Patient)

        then:
            noExceptionThrown()
            patient.diagnostics.tnm == tnm
            patient.diagnostics.t == t
            patient.diagnostics.n == n
            patient.diagnostics.m == m

        where:
            tnm        || t    || n   || m
            'T1aN0M0'  || '1a' || '0' || '0'
            'T1N0M1'   || '1'  || '0' || '1'
            'T2bN2M1b' || '2b' || '2' || '1b'
            'T2bN1M0'  || '2b' || '1' || '0'
            'T4N2M1'   || '4'  || '2' || '1'
            'T4N2M1'   || '4'  || '2' || '1'
            'T2aNxM0'  || '2a' || 'x' || '0'
            'T2aNxM1a' || '2a' || 'x' || '1a'
    }
}
