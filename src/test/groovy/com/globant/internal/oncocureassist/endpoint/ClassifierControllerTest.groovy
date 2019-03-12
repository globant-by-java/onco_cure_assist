package com.globant.internal.oncocureassist.endpoint

import com.globant.internal.oncocureassist.AbstractIntegrationTest
import com.globant.internal.oncocureassist.util.SampleDataProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

import java.time.LocalDate

class ClassifierControllerTest extends AbstractIntegrationTest {

    @Autowired
    private File wekaDir


    def 'verify that we create new classifier if none exists before start application'() {
        when:
            def classifiers = wekaDir.list()

        then:
            noExceptionThrown()
            classifiers != null
            assert classifiers.find { it == '1' }
    }


    def 'verify that we create classifier and increment version'() {
        given: 'create patients'
            def patients = [
                    SampleDataProvider.createPatient([cardNumber : 100,
                                                      contactDate: LocalDate.now().minusMonths(40),
                                                      birthDate  : LocalDate.now().minusMonths(40).minusYears(30),
                                                      classId    : 1]),

                    SampleDataProvider.createPatient([cardNumber : 200,
                                                      contactDate: LocalDate.now().minusMonths(1),
                                                      birthDate  : LocalDate.now().minusMonths(1).minusYears(30),
                                                      classId    : 0])]
            patients.each { createOrUpdatePatient(it) }

        and: 'find classifier with max version'
            def actualClassifiers = wekaDir.listFiles()
            def maxVersion = actualClassifiers.collect({ it.name }).max() as Integer

        when: 'create new classifier'
            def response = createClassifier()

        then: 'new classifier will be created with max + 1 version'
            noExceptionThrown()
            response.statusCode == HttpStatus.OK

            wekaDir.listFiles().size() == actualClassifiers.size() + 1
            def createdClassifier = wekaDir.listFiles().max({ it.name })
            createdClassifier.name as Integer == maxVersion + 1

            def files = createdClassifier.list()
            files != null
            files.size() == 3
            files.find { it == 'patient_template.arff' }
            files.find { it == 'patient_classifier.model' }
            files.find { it == 'decision_tree.png' }
    }


    def 'verify that we handle classifier creation exceptions and remove directory'() {
        given:
            def actualSize = wekaDir.listFiles().size()

        when: 'try to create classifier with empty db'
            def response = createClassifier()

        then: 'exception was occurred'
            noExceptionThrown()
            response.statusCode == HttpStatus.BAD_REQUEST
            response.body.description == 'The classifier cannot be created. Please save patient data and try again'
            !response.body.code
            !response.body.field
            !response.body.object
            wekaDir.listFiles().size() == actualSize
    }


    def 'verify that we can get all classifiers'() {
        given: 'get all files from weka directory'
            def actualClassifiers = wekaDir.listFiles().sort { it.name as Integer }

        when: 'send request to find all classifiers'
            def response = findAllClassifiers()

        then: 'compare actual and received results'
            noExceptionThrown()
            response.statusCode == HttpStatus.OK

            actualClassifiers.size() == response.body.size()
            response.body.eachWithIndex { classifier, index ->
                assert actualClassifiers[index].name == classifier.version as String
                assert classifier.decisionTreePath == actualClassifiers[index].absolutePath + File.separator + 'decision_tree.png'
            }
    }


    def 'verify that we can get classifier by version'() {
        when: 'send request to find classifier by version'
            def response = findClassifierByVersion(1)

        then: 'verify response'
            noExceptionThrown()
            response.statusCode == HttpStatus.OK
            response.body.size() == 2
            response.body.version == 1
            response.body.decisionTreePath == wekaDir.absolutePath + File.separator + 1 + File.separator + 'decision_tree.png'
    }


    def 'verify that we will get 404 if classifier is not found by version'() {
        when: 'send request to find classifier by version'
            def response = findClassifierByVersion(Integer.MAX_VALUE)

        then: 'verify response'
            noExceptionThrown()
            response.statusCode == HttpStatus.NOT_FOUND
            !response.body
    }
}
