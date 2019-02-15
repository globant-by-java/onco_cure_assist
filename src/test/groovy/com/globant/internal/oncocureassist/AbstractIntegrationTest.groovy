package com.globant.internal.oncocureassist

import com.globant.internal.oncocureassist.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.ContextConfiguration
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [OncocureassistApplication])
abstract class AbstractIntegrationTest extends Specification {

    @Autowired
    private TestRestTemplate restTemplate
    @Autowired
    protected PatientRepository patientRepository


    def setup() {
        patientRepository.deleteAll()
    }


    def createPatient(def patient) {
        return restTemplate.postForEntity('/api/patients', patient, Object)
    }


    def findPatients(Map<String, Object> queryParams) {
        def url = '/api/patients'
        if (queryParams) {
            url += '?'
            queryParams.each {
                url += it.key + '=' + it.value + '&'
            }
        }
        return restTemplate.getForEntity(url, Object)
    }


    def deletePatient(def patientId) {
        return restTemplate.exchange("/api/patients/${patientId}", HttpMethod.DELETE, HttpEntity.EMPTY, Object)
    }


    def updatePatient(def patientId, def patient) {
        return restTemplate.exchange("/api/patients/${patientId}", HttpMethod.PUT, new HttpEntity<>(patient), Object)
    }


    def findPatient(def patientId) {
        return restTemplate.getForEntity("/api/patients/${patientId}", Object)
    }


    def getMetadata() {
        return restTemplate.getForEntity('/api/metadata', Object)
    }
}
