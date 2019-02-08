package com.globant.internal.oncocureassist.endpoint

import com.globant.internal.oncocureassist.AbstractIntegrationTest
import com.globant.internal.oncocureassist.util.SampleDataProvider
import org.springframework.http.HttpStatus

class PatientControllerTest extends AbstractIntegrationTest {

    def 'verify that patient was created with all fields'() {
        given: 'create patient request'
            def patient = SampleDataProvider.createPatient()

        when: 'send request to save patient'
            def response = createPatient(patient)

        then: 'patient was stored into db'
            response.statusCode == HttpStatus.CREATED
            !response.body
            def savedPatients = patientRepository.findAll()
            savedPatients.size() == 1

            def treatment = patient.treatment
            def diagnostics = patient.diagnostics
            def geneticPredictors = patient.geneticPredictors

            patient.remove('treatment')
            patient.remove('diagnostics')
            patient.remove('geneticPredictors')

            patient.entrySet().each {
                assert savedPatients[0][it.key] == it.value
            }

            treatment.entrySet().each {
                assert savedPatients[0].treatment[it.key] == it.value
            }

            diagnostics.entrySet().each {
                assert savedPatients[0].diagnostics[it.key] == it.value
            }

            geneticPredictors.entrySet().each {
                assert savedPatients[0].geneticPredictors[it.key] == it.value
            }
    }


    def 'verify that we can find patients with pagination'() {
        given: 'create patients'
            def patients = [
                    SampleDataProvider.createPatient([fullName: 'patient', cardNumber: '1']),
                    SampleDataProvider.createPatient([fullName: 'patient', cardNumber: '2']),
                    SampleDataProvider.createPatient([fullName: 'patient', cardNumber: '3']),
                    SampleDataProvider.createPatient([fullName: 'patient', cardNumber: '4']),
                    SampleDataProvider.createPatient([fullName: 'patient', cardNumber: '5'])
            ]

            patients.each {
                createPatient(it)
            }

        when: 'send request to get all patients'
            def response = findPatients([page: 0, size: 2])

        then: 'all patients were loaded from db'
            response.statusCode == HttpStatus.OK
            response.body.size() == 3
            response.body.totalPages == 3
            response.body.totalElements == 5
            response.body.content.size() == 2
            assert response.body.content.find { it.cardNumber == '1' }
            assert response.body.content.find { it.cardNumber == '2' }
    }


    def 'verify that we can delete patient by id'() {
        given: 'create two patients'
            def patientOne = SampleDataProvider.createPatient([fullName: 'patientOne', cardNumber: '1'])
            def patientTwo = SampleDataProvider.createPatient([fullName: 'patientTwo', cardNumber: '2'])

            createPatient(patientOne)
            createPatient(patientTwo)

        and: 'find patientIds in db'
            def patientIds = patientRepository.findAll().id

        when: 'send request to delete one patient'
            deletePatient(patientIds[0])

        then: 'we must have only one patient in db'
            patientIds.size() == 2
            def patients = patientRepository.findAll()
            patients.size() == 1
            patients[0].id == patientIds[1]
    }


    def 'verify that we can update existing patient'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()
            createPatient(patient)

        and: 'take patient id'
            def savedPatient = patientRepository.findAll()[0]
            assert savedPatient.id
            assert savedPatient.fullName == 'fullName'

        when: 'send request to update patient'
            patient << [fullName: 'updated']
            def response = updatePatient(savedPatient.id, patient)

        then: 'patient was updated'
            response.statusCode == HttpStatus.NO_CONTENT
            !response.body
            def savedPatients = patientRepository.findAll()
            savedPatients.size() == 1

            def treatment = patient.treatment
            def diagnostics = patient.diagnostics
            def geneticPredictors = patient.geneticPredictors

            patient.remove('treatment')
            patient.remove('diagnostics')
            patient.remove('geneticPredictors')

            patient.entrySet().each {
                assert savedPatients[0][it.key] == it.value
            }

            treatment.entrySet().each {
                assert savedPatients[0].treatment[it.key] == it.value
            }

            diagnostics.entrySet().each {
                assert savedPatients[0].diagnostics[it.key] == it.value
            }

            geneticPredictors.entrySet().each {
                assert savedPatients[0].geneticPredictors[it.key] == it.value
            }
    }


    def 'verify that we cannot update not existing patient'() {
        when: 'send not existing patient to update'
            def response = updatePatient(1, [:])

        then: 'validation error occurred'
            response.statusCode == HttpStatus.NOT_FOUND
            !response.body
    }


    def 'verify that we cannot save patient without required fields'() {
        when: 'send empty request to save patient'
            def response = createPatient([:])

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.size() == 7
            response.body.field.containsAll(['fullName', 'gender', 'birthDate', 'contactDate', 'survivalMonth', 'cardNumber', 'alive'])
            response.body.description.findAll { it == 'must not be null' }.size() == 7
    }


    def 'verify that we cannot update patient without required fields'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()
            createPatient(patient)
            def patients = patientRepository.findAll()

        and: 'create empty patient'
            def emptyPatient = SampleDataProvider.createEmptyPatient()

        when: 'send empty request to update existing patient'
            def response = updatePatient(patients[0].id, emptyPatient)

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.size() == 1

        and: 'patient was not changed'
            def savedPatients = patientRepository.findAll()
            savedPatients.size() == 1

            def treatment = patient.treatment
            def diagnostics = patient.diagnostics
            def geneticPredictors = patient.geneticPredictors

            patient.remove('treatment')
            patient.remove('diagnostics')
            patient.remove('geneticPredictors')

            patient.entrySet().each {
                assert savedPatients[0][it.key] == it.value
            }

            treatment.entrySet().each {
                assert savedPatients[0].treatment[it.key] == it.value
            }

            diagnostics.entrySet().each {
                assert savedPatients[0].diagnostics[it.key] == it.value
            }

            geneticPredictors.entrySet().each {
                assert savedPatients[0].geneticPredictors[it.key] == it.value
            }
    }


    def 'verify that we cannot save treatment without required fields'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()

        and: 'remove required fields from treatment'
            def treatment = patient.treatment
            treatment << [surgeryApplied: null]
            treatment << [chemotherapyApplied: null]

        when: 'send request with not valid treatment'
            def response = createPatient(patient)

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.size() == 2
            response.body.field.containsAll(['chemotherapyApplied', 'surgeryApplied'])
            response.body.description.findAll { it == 'must not be null' }.size() == 2
    }


    def 'verify that we cannot save diagnostics without required fields'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()

        and: 'remove required fields from diagnostics'
            patient.diagnostics << [tnm: null]

        when: 'send request with not valid diagnostics'
            def response = createPatient(patient)

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.size() == 1
            response.body.field.containsAll(['tnm'])
            response.body.description.findAll { it == 'must not be null' }.size() == 1
    }


    def 'verify that geneticPredictors does not have required fields'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()

        and: 'remove all fields from geneticPredictors'
            patient.geneticPredictors = [:]

        when: 'send request with empty geneticPredictors'
            def response = createPatient(patient)

        then: 'patient was created with empty geneticPredictors'
            response.statusCode == HttpStatus.CREATED
            !response.body
            def savedPatients = patientRepository.findAll()
            savedPatients.size() == 1
            def savedGeneticPredictors = savedPatients[0].geneticPredictors
            savedGeneticPredictors.id
            savedGeneticPredictors.properties.each {
                if (!['id', 'patient', 'class'].contains(it.key)) {
                    assert !it.value
                }
            }
    }


    def 'verify that we can find patient by id'() {
        given: 'create patients'
            def patients = [SampleDataProvider.createPatient(cardNumber: '1'), SampleDataProvider.createPatient(cardNumber: '2'),]
            patients.each { createPatient(it) }

        and: 'take their ids'
            def patientIds = findPatients().body.content.id

        when: 'send few get requests with id'
           def patientOne = findPatient(patientIds[0])
           def patientTwo = findPatient(patientIds[1])

        then: 'patient one and two were founded'
            noExceptionThrown()
            patientOne.statusCode == HttpStatus.OK
            patientTwo.statusCode == HttpStatus.OK

            patientOne.body.cardNumber == '1'
            patientOne.body.id  == patientIds[0]

            patientTwo.body.cardNumber == '2'
            patientTwo.body.id  == patientIds[1]
    }


    def 'verify that we will get 404 status if patient was not found by id'() {
        when: 'send get requests with any id'
            def response = findPatient(100)

        then: '404 status was received'
            noExceptionThrown()
            response.statusCode == HttpStatus.NOT_FOUND
            !response.body
    }
}
