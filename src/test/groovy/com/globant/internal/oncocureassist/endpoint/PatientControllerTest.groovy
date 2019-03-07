package com.globant.internal.oncocureassist.endpoint

import com.globant.internal.oncocureassist.AbstractIntegrationTest
import com.globant.internal.oncocureassist.domain.dictionary.AuditAction
import com.globant.internal.oncocureassist.util.SampleDataProvider
import org.springframework.http.HttpStatus

import java.time.LocalDate

class PatientControllerTest extends AbstractIntegrationTest {

    def 'verify that patient was created with all fields'() {
        given: 'create patient request'
            def patient = SampleDataProvider.createPatient()

        when: 'send request to save patient'
            def response = createOrUpdatePatient(patient)

        then: 'patient was stored into db'
            response.statusCode == HttpStatus.OK
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
                createOrUpdatePatient(it)
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


    def 'verify that we cannot find deleted patients'() {
        given: 'create patients'
            def patients = [
                    SampleDataProvider.createPatient([fullName: 'patient', cardNumber: '1', deleted: false]),
                    SampleDataProvider.createPatient([fullName: 'patient', cardNumber: '2', deleted: true])
            ]

            patients.each {
                createOrUpdatePatient(it)
            }

        when: 'send request to get all patients'
            def response = findPatients([page: 0, size: 2])

        then: 'only not deleted patient was loaded from db'
            response.statusCode == HttpStatus.OK
            response.body.size() == 3
            response.body.totalPages == 1
            response.body.totalElements == 1
            response.body.content.size() == 1
            assert response.body.content.find { it.cardNumber == '1' }
    }


    def 'verify that we can delete patient by id'() {
        given: 'create two patients'
            def patientOne = SampleDataProvider.createPatient([fullName: 'patientOne', cardNumber: '1'])
            def patientTwo = SampleDataProvider.createPatient([fullName: 'patientTwo', cardNumber: '2'])

            createOrUpdatePatient(patientOne)
            createOrUpdatePatient(patientTwo)

        and: 'find patientIds in db'
            def patientIds = patientRepository.findAll().id

        when: 'send request to delete one patient'
            deletePatient(patientIds[0])

        then: 'we will have one patient with deleted=true'
            patientIds.size() == 2
            def patients = patientRepository.findAll()
            patients.size() == 2
            patients.find { it.deleted && it.id == patientIds[0] }
            patients.find { !it.deleted && it.id == patientIds[1] }
    }


    def 'verify that we can update existing patient'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()
            createOrUpdatePatient(patient)

        and: 'take patient id'
            def savedPatient = patientRepository.findAll()[0]
            assert savedPatient.id
            assert savedPatient.fullName == 'fullName'

        when: 'send request to update patient'
            patient << [id: savedPatient.id, fullName: 'updated', version: savedPatient.version]
            patient.treatment << [id: savedPatient.treatment.id]
            patient.diagnostics << [id: savedPatient.diagnostics.id]
            patient.geneticPredictors << [id: savedPatient.geneticPredictors.id]
            def response = createOrUpdatePatient(patient)

        then: 'patient was updated'
            response.statusCode == HttpStatus.OK
            !response.body
            def savedPatients = patientRepository.findAll()
            savedPatients.size() == 1

            def treatment = patient.treatment
            def diagnostics = patient.diagnostics
            def geneticPredictors = patient.geneticPredictors

            patient.remove('treatment')
            patient.remove('diagnostics')
            patient.remove('geneticPredictors')
            patient << [version: 1]

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


    def 'verify that card number can be non-unique only for a deleted patient'() {
        given: 'create deleted patient'
            def deletedPatient = SampleDataProvider.createPatient(deleted: true, cardNumber: '1000')
            createOrUpdatePatient(deletedPatient)

        when: 'send new patient with same card number'
            def patient = SampleDataProvider.createPatient(deleted: false, cardNumber: '1000')
            def response = createOrUpdatePatient(patient)

        then: 'new patient was created with same card number'
            response.statusCode == HttpStatus.OK
            def patients = patientRepository.findAll().sort { it.id }
            patients.size() == 2
            patients[0].deleted
            !patients[1].deleted
            patients[0].cardNumber == patients[1].cardNumber
    }


    def 'verify patient required fields'() {
        when: 'send empty request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(SampleDataProvider.createEmptyPatient())
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                def emptyPatient = SampleDataProvider.createEmptyPatient()
                emptyPatient << [id: patientId]
                response = createOrUpdatePatient(emptyPatient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'verify patient fields'
            def mandatoryPatientFields = response.body.findAll {
                it.object == 'Patient' && it.description == 'must not be null'
            }
            mandatoryPatientFields.size() == 8
            mandatoryPatientFields.field.containsAll(['fullName', 'gender', 'birthDate', 'contactDate', 'ageClass', 'cardNumber', 'alive', 'age'])

            def patientSpecificFields = response.body.findAll { it.object == 'Patient' && it.code == null }
            patientSpecificFields.size() == 4
            patientSpecificFields.find {
                it.field == 'gender' && it.description == "Acceptable values are 'male' or 'female'"
            }
            patientSpecificFields.find {
                it.field == 'birthDate' && it.description == "Date must be specified between ${LocalDate.of(1900, 1, 1)} and ${LocalDate.now()}"
            }
            patientSpecificFields.find {
                it.field == 'contactDate' && it.description == "Date must be specified between ${LocalDate.of(1900, 1, 1)} and ${LocalDate.now()}"
            }
            patientSpecificFields.find {
                it.field == 'ageClass' && it.description == "Age class was specified incorrectly. Please choose the right range"
            }

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify that patient birthDate must be before contactDate'() {
        when: 'send request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(SampleDataProvider.createPatient([birthDate: LocalDate.now().plusDays(3), contactDate: LocalDate.now().minusDays(1)]))
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                def patient = SampleDataProvider.createPatient([birthDate: LocalDate.now().plusDays(3), contactDate: LocalDate.now().minusDays(1)])
                patient << [id: patientId]
                response = createOrUpdatePatient(patient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.find {
                it.field == 'birthDate' && it.description == "Date must be specified between ${LocalDate.of(1900, 1, 1)} and ${LocalDate.now()}"
            }
            response.body.find {
                it.field == 'birthDate' && it.description == "Birth date must be before contact date"
            }

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify that patient cardNumber must be unique for create action'() {
        given: 'create patient'
            createOrUpdatePatient(SampleDataProvider.createPatient([cardNumber: 'cardNumber']))

        when: 'send request to save patient with the same cardNumber'
            def response = createOrUpdatePatient(SampleDataProvider.createPatient([cardNumber: 'cardNumber']))

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.find {
                it.field == 'cardNumber' && it.description == 'Patient with card number cardNumber already exists'
            }
    }


    def 'verify that we do not validate patient cardNumber for update action'() {
        given: 'create patient'
            createOrUpdatePatient(SampleDataProvider.createPatient([cardNumber: 'cardNumber']))

        when: 'send request to update patient with the same cardNumber'
            def patients = patientRepository.findAll()
            def patient = SampleDataProvider.createPatient([fullName: 'UPDATED', cardNumber: 'cardNumber'])
            patient << [id: patients[0].id, version: patients[0].version]
            patient.treatment << [id: patients[0].treatment.id]
            patient.diagnostics << [id: patients[0].diagnostics.id]
            patient.geneticPredictors << [id: patients[0].geneticPredictors.id]
            def response = createOrUpdatePatient(patient)

        then: 'patient was updated'
            response.statusCode == HttpStatus.OK
            !response.body
            def savedPatient = patientRepository.findAll()[0]
            savedPatient.fullName == 'UPDATED'
            savedPatient.cardNumber == 'cardNumber'
    }


    def 'verify treatment required fields'() {
        when: 'send empty request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(SampleDataProvider.createEmptyPatient())
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                def patient = SampleDataProvider.createEmptyPatient()
                patient << [id: patientId]
                response = createOrUpdatePatient(patient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'verify treatment fields'
            def mandatoryTreatmentFields = response.body.findAll {
                it.object == 'Treatment' && it.description == 'must not be null'
            }
            mandatoryTreatmentFields.size() == 2
            mandatoryTreatmentFields.field.containsAll(['chemotherapyApplied', 'surgeryApplied'])

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify diagnostics required fields'() {
        when: 'send empty request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(SampleDataProvider.createEmptyPatient())
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                def patient = SampleDataProvider.createEmptyPatient()
                patient << [id: patientId]
                response = createOrUpdatePatient(patient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'verify diagnostics fields'
            def mandatoryDiagnosticsFields = response.body.findAll {
                it.object == 'Diagnostics' && it.description == 'must not be null'
            }
            mandatoryDiagnosticsFields.size() == 1
            mandatoryDiagnosticsFields.field.containsAll(['tnm'])

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify patient field length restrictions and min/max limits'() {
        given: 'create patient with some invalid fields'
            def patient = SampleDataProvider.createPatient([fullName  : 'fullName'.center(128), professionCode: 0,
                                                            address   : 'address'.center(513), phone: 'phone'.center(129),
                                                            cardNumber: 'cardNumber'.center(65)])

        when: 'send not valid request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(patient)
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                patient << [id: patientId]
                response = createOrUpdatePatient(patient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'verify patient'
            def patientErrors = response.body.findAll { it.object == 'Patient' }
            patientErrors.size() == 5
            patientErrors.find { it.field == 'address' && it.description == 'size must be between 0 and 512' }
            patientErrors.find { it.field == 'cardNumber' && it.description == 'size must be between 0 and 64' }
            patientErrors.find {
                it.field == 'professionCode' && it.description == 'must be greater than or equal to 1'
            }
            patientErrors.find { it.field == 'phone' && it.description == 'size must be between 0 and 128' }
            patientErrors.find { it.field == 'fullName' && it.description == 'size must be between 0 and 127' }

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify treatment field length restrictions and min/max limits'() {
        given: 'create patient with some invalid fields'
            def patient = SampleDataProvider.createPatient()

            patient.treatment << [surgeryCode     : -1,
                                  surgeryDate     : LocalDate.now().plusDays(1),
                                  surgeonName     : 'surgeonName'.center(128),
                                  firstLineCourse : -1,
                                  secondLineCourse: -1,
                                  thirdLineCourse : -1
            ]

        when: 'send not valid request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(patient)
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                patient << [id: patientId]
                response = createOrUpdatePatient(patient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'verify treatment'
            def treatmentErrors = response.body.findAll { it.object == 'Treatment' }
            treatmentErrors.size() == 6
            treatmentErrors.find {
                it.field == 'firstLineCourse' && it.description == 'must be greater than or equal to 0'
            }
            treatmentErrors.find {
                it.field == 'secondLineCourse' && it.description == 'must be greater than or equal to 0'
            }
            treatmentErrors.find {
                it.field == 'thirdLineCourse' && it.description == 'must be greater than or equal to 0'
            }
            treatmentErrors.find { it.field == 'surgeonName' && it.description == 'size must be between 0 and 127' }
            treatmentErrors.find { it.field == 'surgeryCode' && it.description == 'must be greater than or equal to 0' }
            treatmentErrors.find {
                it.field == 'surgeryDate' && it.description == "Date must be specified between ${LocalDate.of(1900, 1, 1)} and ${LocalDate.now()}"
            }

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify diagnostics field length restrictions and min/max limits'() {
        given: 'create patient with some invalid fields'
            def patient = SampleDataProvider.createPatient()

            patient.diagnostics << [tnm               : 'T5AN5M2',
                                    tumourSize        : 5,
                                    side              : 3,
                                    vlc               : -1,
                                    tvc               : -1,
                                    tiffNumber        : -1,
                                    volumeForceExp    : -1,
                                    heartRate         : 0,
                                    stage             : 'stage'.center(17),
                                    histologyDiagnosis: 27,
                                    histologyCode     : 'histologyCode'.center(65)]

        when: 'send not valid request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(patient)
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                patient << [id: patientId]
                response = createOrUpdatePatient(patient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'verify diagnostics fields'
            def diagnosticsErrors = response.body.findAll { it.object == 'Diagnostics' }
            diagnosticsErrors.size() == 11
            diagnosticsErrors.find { it.field == 'tumourSize' && it.description == 'must be less than or equal to 4' }
            diagnosticsErrors.find {
                it.field == 'histologyDiagnosis' && it.description == 'must be less than or equal to 26'
            }
            diagnosticsErrors.find { it.field == 'stage' && it.description == 'size must be between 0 and 16' }
            diagnosticsErrors.find { it.field == 'heartRate' && it.description == 'must be greater than or equal to 1' }
            diagnosticsErrors.find { it.field == 'histologyCode' && it.description == 'size must be between 0 and 64' }
            diagnosticsErrors.find { it.field == 'vlc' && it.description == 'must be greater than or equal to 0' }
            diagnosticsErrors.find { it.field == 'tvc' && it.description == 'must be greater than or equal to 0' }
            diagnosticsErrors.find {
                it.field == 'tiffNumber' && it.description == 'must be greater than or equal to 0'
            }
            diagnosticsErrors.find { it.field == 'side' && it.description == 'must be less than or equal to 2' }
            diagnosticsErrors.find {
                it.field == 'volumeForceExp' && it.description == 'must be greater than or equal to 0'
            }
            diagnosticsErrors.find {
                it.field == 'tnm' && it.description == 'must match "T(x|[0-4][a-b]?)N(x|[0-3][a-b]?)M[0-1]"'
            }

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify genetic predictors fields validation'() {
        given: 'create patient with some invalid fields'
            def patient = SampleDataProvider.createPatient()

            patient.geneticPredictors << [vegf634        : 'vegf634',
                                          vegf2578       : 'vegf2578',
                                          vegf936        : 'vegf936',
                                          egf            : 'egf',
                                          gstt           : 'gstt',
                                          gstm           : 'gstm',
                                          gstp           : 'gstp',
                                          natkpn         : 'natkpn',
                                          nattag         : 'nattag',
                                          natbam         : 'natbam',
                                          acetylationType: 'acetylationType',
                                          cyp1a2         : 'cyp1a2',
                                          cyp2d6         : 'cyp2d6',
                                          mdr            : 'mdr',
                                          egfr18Tumor    : 'egfr18Tumor',
                                          egfr18Norm     : 'egfr18Norm',
                                          egfr19Tumor    : 'egfr19Tumor',
                                          egfr19Norm     : 'egfr19Norm',
                                          egfr20Tumor    : 'egfr20Tumor',
                                          egfr20Norm     : 'egfr20Norm',
                                          egfr21Tumor    : 'egfr21Tumor',
                                          egfr21norm     : 'egfr21norm',
                                          egfr21Blood    : 'egfr21Blood',
                                          tgf509         : 'tgf509',
                                          tgf25Codon     : 'tgf25Codon',
                                          tgfr206        : 'tgfr206',
                                          kdr1719        : 'kdr1719',
                                          kdr906         : 'kdr906',
                                          sult1          : 'sult1',
                                          mmp92660       : 'mmp92660',
                                          mmp91562       : 'mmp91562',
                                          mmp2735        : 'mmp2735',
                                          mmp21575       : 'mmp21575',
                                          kras2exTumor   : 'kras2exTumor',
                                          kras2exNorm    : 'kras2exNorm',
                                          pik3ca9ex      : 'pik3ca9ex',
                                          pik3ca20ex     : 'pik3ca20ex',
                                          pten           : 'pten',
                                          dnmt149        : 'dnmt149',
                                          dnmt579        : 'dnmt579'
            ]

        when: 'send not valid request to save or update patient'
            def response
            if (action == 'CREATE') {
                response = createOrUpdatePatient(patient)
            } else {
                createOrUpdatePatient(SampleDataProvider.createPatient())
                def patientId = patientRepository.findAll()[0].id
                patient << [id: patientId]
                response = createOrUpdatePatient(patient)
            }

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'verify genetic predictions fields'
            def geneticPredictorsErrors = response.body.findAll { it.object == 'GeneticPredictors' }
            geneticPredictorsErrors.size() == 40

            geneticPredictorsErrors.find {
                it.field == 'vegf634' && it.description == 'The code was specified incorrectly. Expected values [G/G, G/C, C/C]'
            }
            geneticPredictorsErrors.find {
                it.field == 'vegf2578' && it.description == 'The code was specified incorrectly. Expected values [C/C, A/C, A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'vegf936' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T, T/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egf' && it.description == 'The code was specified incorrectly. Expected values [A/A, G/A, G/G]'
            }
            geneticPredictorsErrors.find {
                it.field == 'gstt' && it.description == 'The code was specified incorrectly. Expected values [1, 0]'
            }
            geneticPredictorsErrors.find {
                it.field == 'gstm' && it.description == 'The code was specified incorrectly. Expected values [1, 0]'
            }
            geneticPredictorsErrors.find {
                it.field == 'gstp' && it.description == 'The code was specified incorrectly. Expected values [A/A, G/A, G/G]'
            }
            geneticPredictorsErrors.find {
                it.field == 'natkpn' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T, T/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'nattag' && it.description == 'The code was specified incorrectly. Expected values [G/G, G/A, A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'natbam' && it.description == 'The code was specified incorrectly. Expected values [G/G, G/A, A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'acetylationType' && it.description == 'The code was specified incorrectly. Expected values [1, 0]'
            }
            geneticPredictorsErrors.find {
                it.field == 'cyp1a2' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/A, A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'cyp2d6' && it.description == 'The code was specified incorrectly. Expected values [G/G, G/A, A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'mdr' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T, T/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr18Tumor' && it.description == 'The code was specified incorrectly. Expected values [no, c.2203G/A, c.2203G/G, c.2203A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr18Norm' && it.description == 'The code was specified incorrectly. Expected values [no]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr19Tumor' && it.description == 'The code was specified incorrectly. Expected values [no, 2235_2249del, 2236_2250del, 2240_2257del]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr19Norm' && it.description == 'The code was specified incorrectly. Expected values [no]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr20Tumor' && it.description == 'The code was specified incorrectly. Expected values [no, c.2361A/A, c.2361G/G, c.2361G/A, p.A763_Y764insFQEA]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr20Norm' && it.description == 'The code was specified incorrectly. Expected values [no, c.2361G/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr21Tumor' && it.description == 'The code was specified incorrectly. Expected values [no, 2508C/T, L858R]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr21norm' && it.description == 'The code was specified incorrectly. Expected values [no, 2508C/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'egfr21Blood' && it.description == 'The code was specified incorrectly. Expected values [no, 2508C/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'tgf509' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T, T/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'tgf25Codon' && it.description == 'The code was specified incorrectly. Expected values [G/G, G/C, C/C]'
            }
            geneticPredictorsErrors.find {
                it.field == 'tgfr206' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T, T/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'kdr1719' && it.description == 'The code was specified incorrectly. Expected values [T/T, T/A, A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'kdr906' && it.description == 'The code was specified incorrectly. Expected values [T/T, T/C, C/C]'
            }
            geneticPredictorsErrors.find {
                it.field == 'sult1' && it.description == 'The code was specified incorrectly. Expected values [GA, GG]'
            }
            geneticPredictorsErrors.find {
                it.field == 'mmp92660' && it.description == 'The code was specified incorrectly. Expected values [A/A, G/A, G/G]'
            }
            geneticPredictorsErrors.find {
                it.field == 'mmp91562' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'mmp2735' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T, T/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'mmp21575' && it.description == 'The code was specified incorrectly. Expected values [G/G, G/A, A/A]'
            }
            geneticPredictorsErrors.find {
                it.field == 'kras2exTumor' && it.description == 'The code was specified incorrectly. Expected values [no, c.35G/A, c.37G/T, c.34G/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'kras2exNorm' && it.description == 'The code was specified incorrectly. Expected values [no]'
            }
            geneticPredictorsErrors.find {
                it.field == 'pik3ca9ex' && it.description == 'The code was specified incorrectly. Expected values [no]'
            }
            geneticPredictorsErrors.find {
                it.field == 'pik3ca20ex' && it.description == 'The code was specified incorrectly. Expected values [no]'
            }
            geneticPredictorsErrors.find {
                it.field == 'pten' && it.description == 'The code was specified incorrectly. Expected values [no]'
            }
            geneticPredictorsErrors.find {
                it.field == 'dnmt149' && it.description == 'The code was specified incorrectly. Expected values [C/C, C/T, T/T]'
            }
            geneticPredictorsErrors.find {
                it.field == 'dnmt579' && it.description == 'The code was specified incorrectly. Expected values [G/G, G/T, T/T]'
            }

        where:
            action << ['CREATE', 'UPDATE']
    }


    def 'verify that genetic predictors fields are not mandatory'() {
        given: 'create patient with invalid fields'
            def patient = SampleDataProvider.createEmptyPatient()

        when: 'send not valid request to save patient'
            def response = createOrUpdatePatient(patient)


        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
        and: 'genetic predictions fields are absent'
            def geneticPredictorsErrors = response.body.findAll { it.object == 'GeneticPredictors' }
            geneticPredictorsErrors.size() == 0
    }


    def 'verify that we cannot update patient without required fields'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()
            createOrUpdatePatient(patient)
            def patients = patientRepository.findAll()

        and: 'create empty patient'
            def emptyPatient = SampleDataProvider.createEmptyPatient()

        when: 'send empty request to update existing patient'
            emptyPatient << [id: patients[0].id,]
            def response = createOrUpdatePatient(emptyPatient)

        then: 'validation error occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.size() == 15

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
            def response = createOrUpdatePatient(patient)

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
            def response = createOrUpdatePatient(patient)

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
            def response = createOrUpdatePatient(patient)

        then: 'patient was created with empty geneticPredictors'
            response.statusCode == HttpStatus.OK
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


    def 'verify that we can find only not deleted patient by id'() {
        given: 'create patients'
            def patients = [SampleDataProvider.createPatient(cardNumber: '1', deleted: true),
                            SampleDataProvider.createPatient(cardNumber: '2', deleted: false)]
            patients.each { createOrUpdatePatient(it) }

        and: 'take their ids'
            def patientIds = patientRepository.findAll().id

        when: 'send few get requests with id'
            def patientOne = findPatient(patientIds[0])
            def patientTwo = findPatient(patientIds[1])

        then: 'patient one and two were founded'
            noExceptionThrown()
            patientOne.statusCode == HttpStatus.NOT_FOUND
            !patientOne.body

            patientTwo.statusCode == HttpStatus.OK
            patientTwo.body.cardNumber == '2'
            patientTwo.body.id == patientIds[1]
    }


    def 'verify that we will get 404 status if patient was not found by id'() {
        when: 'send get requests with any id'
            def response = findPatient(100)

        then: '404 status was received'
            noExceptionThrown()
            response.statusCode == HttpStatus.NOT_FOUND
            !response.body
    }


    def 'verify that some fields were enrich before save or update'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient([contactDate: LocalDate.now().minusMonths(36), age: 27, ageClass: 2])
            patient.treatment << [surgeryDate: LocalDate.now()]
            patient.diagnostics << [tnm: 'T1aN0M0']

        and: 'reset some fields'
            patient << [survivalMonth: null, classId: null]
            patient.diagnostics << [t: null, n: null, m: null]

        when: 'send request to save patient'
            def saveResponse = createOrUpdatePatient(patient)

        then: 'some fields were populated'
            saveResponse.statusCode == HttpStatus.OK
            def savedPatients = patientRepository.findAll()
            savedPatients.size() == 1
            savedPatients[0].survivalMonth == 36
            savedPatients[0].classId == 1
            savedPatients[0].diagnostics.t == '1a'
            savedPatients[0].diagnostics.n == '0'
            savedPatients[0].diagnostics.m == '0'

        when: 'send request to update patient'
            patient << [id: savedPatients[0].id, contactDate: LocalDate.now().minusMonths(35), age: 27, ageClass: 2, version: savedPatients[0].version]
            patient.treatment << [id: savedPatients[0].treatment.id, surgeryDate: LocalDate.now().minusMonths(1)]
            patient.diagnostics << [id: savedPatients[0].diagnostics.id, tnm: 'T0aNxM1']
            patient.geneticPredictors << [id: savedPatients[0].geneticPredictors.id]

            def updateResponse = createOrUpdatePatient(patient)

        then: 'some fields were populated'
            updateResponse.statusCode == HttpStatus.OK
            def updatedPatients = patientRepository.findAll()
            updatedPatients.size() == 1
            updatedPatients[0].survivalMonth == 34
            updatedPatients[0].classId == 0
            updatedPatients[0].diagnostics.t == '0a'
            updatedPatients[0].diagnostics.n == 'x'
            updatedPatients[0].diagnostics.m == '1'
    }


    def 'verify that we add new audit row when patient is created'() {
        given: 'create patient request'
            def patient = SampleDataProvider.createPatient()

        when: 'send request to save patient'
            def response = createOrUpdatePatient(patient)

        then: 'patient was stored into db'
            response.statusCode == HttpStatus.OK
            !response.body
            def savedPatients = patientRepository.findAll()
            savedPatients.size() == 1

        and: 'new audit row was created'
            def audits = auditRepository.findAll()
            audits.size() == 1
            audits[0].entityId == savedPatients[0].id
            audits[0].action == AuditAction.CREATE
            audits[0].userName == 'default'
            audits[0].createdDate.toLocalDate() == LocalDate.now()
            audits[0].content == objectMapper.writeValueAsString(savedPatients[0])

        when: 'we create one more patient'
            response = createOrUpdatePatient(SampleDataProvider.createPatient([fullName: 'patient2', cardNumber: 'patient2']))

        then: 'one more new row was added to audit table'
            response.statusCode == HttpStatus.OK
            !response.body
            def patients = patientRepository.findAll().sort { it.id }
            patients.size() == 2

            def savedAudits = auditRepository.findAll().sort { it.id }
            savedAudits.size() == 2
            savedAudits[1].entityId == patients[1].id
            savedAudits[1].action == AuditAction.CREATE
            savedAudits[1].userName == 'default'
            savedAudits[1].createdDate.toLocalDate() == LocalDate.now()
            savedAudits[1].content == objectMapper.writeValueAsString(patients[1])
    }


    def 'verify that we add new audit row when we update existing patient'() {
        given: 'create patient'
            def patient = SampleDataProvider.createPatient()
            createOrUpdatePatient(patient)

        and: 'take patient id'
            def createdPatient = patientRepository.findAll()[0]
            assert createdPatient.id
            assert createdPatient.fullName == 'fullName'

        when: 'send request to update patient'
            patient << [id: createdPatient.id, fullName: 'updated', version: createdPatient.version]
            patient.treatment << [id: createdPatient.treatment.id]
            patient.diagnostics << [id: createdPatient.diagnostics.id]
            patient.geneticPredictors << [id: createdPatient.geneticPredictors.id]
            def response = createOrUpdatePatient(patient)

        then: 'patient was updated'
            response.statusCode == HttpStatus.OK
            !response.body
            def updatedPatients = patientRepository.findAll()
            updatedPatients.size() == 1

        and: 'new audit row was created'
            def audits = auditRepository.findAll().sort { it.id }
            audits.size() == 2

            def auditCreated = audits.find { it.action == AuditAction.CREATE }
            auditCreated.entityId == updatedPatients[0].id
            auditCreated.action == AuditAction.CREATE
            auditCreated.userName == 'default'
            auditCreated.createdDate.toLocalDate() == LocalDate.now()
            auditCreated.content == objectMapper.writeValueAsString(createdPatient)

            def auditUpdated = audits.find { it.action == AuditAction.UPDATE }
            auditUpdated.entityId == updatedPatients[0].id
            auditUpdated.action == AuditAction.UPDATE
            auditUpdated.userName == 'default'
            auditUpdated.createdDate.toLocalDate() == LocalDate.now()
            auditUpdated.content.contains('"fullName":"updated"')
    }


    def 'verify that we add new audit row when we delete patient by id'() {
        given: 'create two patients'
            def patientOne = SampleDataProvider.createPatient([fullName: 'patientOne', cardNumber: '1'])
            def patientTwo = SampleDataProvider.createPatient([fullName: 'patientTwo', cardNumber: '2'])

            createOrUpdatePatient(patientOne)
            createOrUpdatePatient(patientTwo)

        and: 'find patientIds in db'
            def patientIds = patientRepository.findAll().id

        when: 'send request to delete one patient'
            deletePatient(patientIds[0])

        then: 'we will have one patient with deleted=true'
            patientIds.size() == 2
            def patients = patientRepository.findAll()
            def deletedPatient = patients.find { it.deleted }

        and: 'new audit row was created'
            def audits = auditRepository.findAll().sort { it.id }
            audits.size() == 3

            def auditDeleted = audits.find { it.action == AuditAction.DELETE }
            auditDeleted.entityId == deletedPatient.id
            auditDeleted.action == AuditAction.DELETE
            auditDeleted.userName == 'default'
            auditDeleted.createdDate.toLocalDate() == LocalDate.now()
            auditDeleted.content.contains('"deleted":true')
    }


    def 'verify that only first user can update patient'() {
        given: 'create patient'
            def patientOne = SampleDataProvider.createPatient()
            createOrUpdatePatient(patientOne)

        and: 'find patient in db'
            def savedPatient = patientRepository.findAll()[0]

        when: 'first user send request to delete patient'
            deletePatient(savedPatient.id)

        then: 'patient was deleted and version was incremented'
            def patients = patientRepository.findAll()
            def deletedPatient = patients.find { it.deleted }
            deletedPatient.version == 1

        when: 'second user send request to update patient with version=0'
            savedPatient.treatment.surgeryApplied = false
            def response = createOrUpdatePatient(savedPatient)

        then: 'optimistic lock exception has occurred'
            response.statusCode == HttpStatus.CONFLICT
            response.body.description == 'Patient was already updated by another person. Please refresh the page'
    }


    def 'verify that we can classify patient without classId'() {
        given: 'create two patient requests'
            def patientOne = SampleDataProvider.createPatient([])


            def patientTwo = SampleDataProvider.createPatient([
                    gender       : 1,
                    birthDate    : "1952-11-16",
                    contactDate  : "2007-08-03",
                    survivalMonth: 10.0,
                    cardNumber   : "12792/03",
                    classId      : null,
                    age          : 55,
                    ageClass     : 5])

            patientTwo.diagnostics << [tnm                               : "T2N0M0",
                                       tumourSize                        : 1,
                                       bronchialCarcinoma                : false,
                                       lungCarcinoma                     : true,
                                       peribroncialCarcinoma             : false,
                                       interlobar                        : false,
                                       subcarinal                        : false,
                                       lowerParatracheal                 : false,
                                       upperParatracheal                 : false,
                                       oncoAnamesys                      : false,
                                       stage                             : "1",
                                       complains                         : false,
                                       grade                             : 2,
                                       histologyDiagnosis                : 1,
                                       smoking                           : true,
                                       t                                 : "2",
                                       n                                 : "0",
                                       m                                 : "0",
                                       copd                              : true,
                                       tuberculomas                      : false,
                                       chd                               : false,
                                       lcd                               : false,
                                       inflammatoryDigestiveProcesses    : true,
                                       hepatitis                         : false,
                                       cirrhosis                         : false,
                                       pancreatitis                      : false,
                                       musculoskeletalDiseases           : false,
                                       inflammatoryKidneysBladderDiseases: false,
                                       prostateBenignDiseases            : false,
                                       veinsDiseases                     : false,
                                       bloodVesselsDiseases              : false,
                                       rhythmDisturbances                : false,
                                       thyroidGlandBenignDiseases        : false,
                                       nervousDiseases                   : false,
                                       strokes                           : false,
                                       rheumaticDiseases                 : false,
                                       anemia                            : false
            ]

            patientTwo.treatment << [surgeryApplied         : true,
                                     chemotherapyApplied    : true,
                                     radiationTherapyApplied: false,
                                     surgeryCode            : 0,
                                     surgeonName            : null,
                                     surgeryDate            : "2006-11-30",
                                     firstLineCourse        : 2,
                                     secondLineCourse       : null,
                                     thirdLineCourse        : null
            ]

            patientTwo.geneticPredictors << [vegf634        : "G/G",
                                             vegf2578       : "A/A",
                                             vegf936        : "C/T",
                                             egf            : "G/A",
                                             gstt           : "1",
                                             gstm           : "0",
                                             gstp           : "G/A",
                                             natkpn         : "C/C",
                                             nattag         : "G/A",
                                             natbam         : "G/G",
                                             acetylationType: "1",
                                             cyp1a2         : "C/A",
                                             cyp2d6         : "G/A",
                                             mdr            : "C/T",
                                             egfr18Tumor    : null,
                                             egfr18Norm     : null,
                                             egfr19Tumor    : null,
                                             egfr19Norm     : null,
                                             egfr20Tumor    : null,
                                             egfr20Norm     : null,
                                             egfr21Tumor    : null,
                                             egfr21norm     : null,
                                             egfr21Blood    : null,
                                             tgf509         : "C/T",
                                             tgf25Codon     : null,
                                             tgfr206        : null,
                                             kdr1719        : "T/T",
                                             kdr906         : "C/C",
                                             sult1          : null,
                                             mmp92660       : "A/A",
                                             mmp91562       : null,
                                             mmp2735        : "C/C",
                                             mmp21575       : "G/A",
                                             kras2exTumor   : null,
                                             kras2exNorm    : null,
                                             pik3ca9ex      : null,
                                             pik3ca20ex     : null,
                                             pten           : null,
                                             dnmt149        : null,
                                             dnmt579        : null
            ]


        when: 'send patient to classify'
            def responseOne = classifyPatient(patientOne, [version: 1])

        then: 'class id was predicted'
            responseOne.statusCode == HttpStatus.OK
            responseOne.body == 0

        when: 'send patient to classify'
            def responseTwo = classifyPatient(patientTwo, [version: 1])

        then: 'class id was predicted'
            responseTwo.statusCode == HttpStatus.OK
            responseTwo.body == 1
    }


    def 'verify that we handle errors when classify patient'() {
        given: 'create patient request with not valid data'
            def patient = SampleDataProvider.createPatient([ageClass: 1])

        when: 'send patient to classify'
            def response = classifyPatient(patient, [version: 1])

        then: 'error was occurred'
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.description == 'The patient cannot be classified. Please update the classifier and try again'
            !response.body.code
            !response.body.field
            !response.body.object
    }
}
