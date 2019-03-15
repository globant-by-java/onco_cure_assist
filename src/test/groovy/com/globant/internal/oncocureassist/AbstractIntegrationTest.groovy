package com.globant.internal.oncocureassist

import com.fasterxml.jackson.databind.ObjectMapper
import com.globant.internal.oncocureassist.repository.AuditRepository
import com.globant.internal.oncocureassist.repository.PatientRepository
import com.globant.internal.oncocureassist.util.TestConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.context.ContextConfiguration
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
@ContextConfiguration(classes = [OncocureassistApplication, TestConfig])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractIntegrationTest extends Specification {

    @Autowired
    private TestRestTemplate restTemplate
    @Autowired
    protected PatientRepository patientRepository
    @Autowired
    protected AuditRepository auditRepository
    @Autowired
    protected ObjectMapper objectMapper


    def setup() {
        patientRepository.deleteAll()
        auditRepository.deleteAll()
    }


    def createOrUpdatePatient(def patient) {
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


    def findPatient(def patientId) {
        return restTemplate.getForEntity("/api/patients/${patientId}", Object)
    }


    def classifyPatient(def patient, Map<String, Object> queryParams) {
        def url = '/api/patients/classify'
        if (queryParams) {
            url += '?'
            queryParams.each {
                url += it.key + '=' + it.value + '&'
            }
        }
        return restTemplate.postForEntity(url, patient, Object)
    }


    def getMetadata() {
        return restTemplate.getForEntity('/api/metadata', Object)
    }


    def createClassifier() {
        return restTemplate.postForEntity('/api/classifiers', HttpHeaders.EMPTY, Object)
    }
}
