package com.globant.internal.oncocureassist.service.impl;

import com.globant.internal.oncocureassist.domain.dictionary.AuditAction;
import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import com.globant.internal.oncocureassist.domain.exception.PatientValidationException;
import com.globant.internal.oncocureassist.repository.PatientRepository;
import com.globant.internal.oncocureassist.repository.entity.Patient;
import com.globant.internal.oncocureassist.service.AuditService;
import com.globant.internal.oncocureassist.service.PatientService;
import com.globant.internal.oncocureassist.service.enricher.Enricher;
import com.globant.internal.oncocureassist.service.validator.ConstraintValidator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AuditService auditService;
    private final ConstraintValidator<Patient> patientValidator;
    private final Enricher patientEnricher;


    PatientServiceImpl(PatientRepository patientRepository,
                       AuditService auditService,
                       ConstraintValidator<Patient> patientValidator,
                       Enricher patientEnricher) {
        this.patientRepository = patientRepository;
        this.auditService = auditService;
        this.patientValidator = patientValidator;
        this.patientEnricher = patientEnricher;
    }


    @Transactional
    @Override
    public void create(Patient patient) {
        validatePatient(patient);
        patientEnricher.enrich(patient);
        patientRepository.save(patient);
        auditService.add(patient, AuditAction.CREATE);
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
    public Optional<Patient> update(Long id, Patient patient) {
        Optional<Patient> savedPatient = patientRepository.findOne(notDeleted(id));

        if (!savedPatient.isPresent()) {
            return Optional.empty();
        }

        validatePatient(patient);
        savedPatient.ifPresent(p -> {
            patient.setId(p.getId());

            Optional.ofNullable(p.getTreatment())
                    .ifPresent(obj -> patient.getTreatment().setId(obj.getId()));

            Optional.ofNullable(p.getDiagnostics())
                    .ifPresent(obj -> patient.getDiagnostics().setId(obj.getId()));

            Optional.ofNullable(p.getGeneticPredictors())
                    .ifPresent(obj -> patient.getGeneticPredictors().setId(obj.getId()));

            patientEnricher.enrich(patient);
            patientRepository.save(patient);
            auditService.add(patient, AuditAction.UPDATE);
        });

        return savedPatient;
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


    private void validatePatient(Patient patient) {
        List<ConstraintError> errors = patientValidator.validate(patient);
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
