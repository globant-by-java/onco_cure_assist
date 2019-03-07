package com.globant.internal.oncocureassist.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.internal.oncocureassist.domain.dictionary.AuditAction;
import com.globant.internal.oncocureassist.domain.dictionary.ValidationType;
import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import com.globant.internal.oncocureassist.domain.exception.PatientValidationException;
import com.globant.internal.oncocureassist.domain.model.PatientClassifyModel;
import com.globant.internal.oncocureassist.mapper.PatientClassifyModelMapper;
import com.globant.internal.oncocureassist.repository.PatientRepository;
import com.globant.internal.oncocureassist.repository.entity.Patient;
import com.globant.internal.oncocureassist.service.AuditService;
import com.globant.internal.oncocureassist.service.ClassifierService;
import com.globant.internal.oncocureassist.service.PatientService;
import com.globant.internal.oncocureassist.service.enricher.Enricher;
import com.globant.internal.oncocureassist.service.validator.ConstraintValidator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AuditService auditService;
    private final ConstraintValidator<Patient> patientValidator;
    private final Enricher patientEnricher;
    private final ClassifierService classifierService;
    private final PatientClassifyModelMapper patientClassifyModelMapper;
    private final ObjectMapper objectMapper;


    PatientServiceImpl(PatientRepository patientRepository,
                       AuditService auditService,
                       ConstraintValidator<Patient> patientValidator,
                       Enricher patientEnricher,
                       ClassifierService classifierService,
                       PatientClassifyModelMapper patientClassifyModelMapper,
                       ObjectMapper objectMapper) {
        this.patientRepository = patientRepository;
        this.auditService = auditService;
        this.patientValidator = patientValidator;
        this.patientEnricher = patientEnricher;
        this.classifierService = classifierService;
        this.patientClassifyModelMapper = patientClassifyModelMapper;
        this.objectMapper = objectMapper;
    }


    @Transactional
    @Override
    public void createOrUpdate(Patient patient) {
        boolean patientExists = Optional.ofNullable(patient.getId())
                .flatMap(id -> patientRepository.findOne(notDeleted(id)))
                .isPresent();

        ValidationType validationType = patientExists ? ValidationType.UPDATE : ValidationType.CREATE;
        AuditAction auditAction = patientExists ? AuditAction.UPDATE : AuditAction.CREATE;

        validatePatient(patient, validationType);
        patientEnricher.enrich(patient);
        patientRepository.save(patient);
        auditService.add(patient, auditAction);
    }


    @Transactional(readOnly = true)
    @Override
    public Page<Patient> find(Pageable pageable) {
        return patientRepository.findAll(notDeleted(null), pageable);
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findOne(notDeleted(id));
    }


    @Transactional
    @Override
    public void delete(Long id) {
        patientRepository.findById(id)
                .ifPresent(patient -> {
                    patient.setDeleted(true);
                    patientRepository.save(patient);
                    auditService.add(patient, AuditAction.DELETE);
                });
    }


    @Override
    public int classifyPatient(Patient patient, Integer version) {
        PatientClassifyModel classifyModel = patientClassifyModelMapper.toModel(patient);
        TypeReference<Map<String, String>> type = new TypeReference<Map<String, String>>() {};
        Map<String, String> modelMap = objectMapper.convertValue(classifyModel, type);

        return (int) classifierService.classify(modelMap, version);
    }


    private void validatePatient(Patient patient, ValidationType validationType) {
        List<ConstraintError> errors = patientValidator.validate(patient, validationType);
        if (!errors.isEmpty()) {
            throw new PatientValidationException(errors);
        }
    }


    private Example<Patient> notDeleted(Long id) {
        Patient patientCriteria = new Patient();
        patientCriteria.setId(id);
        patientCriteria.setDeleted(false);

        return Example.of(patientCriteria);
    }
}
