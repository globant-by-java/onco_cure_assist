package com.globant.internal.oncocureassist.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.internal.oncocureassist.domain.dictionary.AuditAction;
import com.globant.internal.oncocureassist.domain.exception.PatientJsonGenerationException;
import com.globant.internal.oncocureassist.repository.AuditRepository;
import com.globant.internal.oncocureassist.repository.entity.Audit;
import com.globant.internal.oncocureassist.repository.entity.Patient;
import com.globant.internal.oncocureassist.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

@Service
class AuditServiceImpl implements AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditServiceImpl.class);
    private static final String DEFAULT_USER = "default";

    private final AuditRepository auditRepository;
    private final ObjectMapper objectMapper;


    AuditServiceImpl(AuditRepository auditRepository, ObjectMapper objectMapper) {
        this.auditRepository = auditRepository;
        this.objectMapper = objectMapper;
    }


    @Transactional
    @Override
    public void add(Patient patient, AuditAction action) {
        Audit audit = new Audit();
        audit.setEntityId(patient.getId());
        audit.setAction(action);
        audit.setUserName(getUser());
        audit.setContent(toJson(patient));

        auditRepository.save(audit);
    }


    private String getUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(principal -> (String) principal)
                .orElse(DEFAULT_USER);
    }


    private String toJson(Patient patient) {
        try {
            return objectMapper.writeValueAsString(patient);
        } catch (JsonProcessingException exc) {
            log.error("Patient cannot be converted to json", exc);
            throw new PatientJsonGenerationException();
        }
    }
}
