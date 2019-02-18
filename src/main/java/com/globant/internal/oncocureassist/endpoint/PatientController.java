package com.globant.internal.oncocureassist.endpoint;

import com.globant.internal.oncocureassist.domain.exception.ConstraintError;
import com.globant.internal.oncocureassist.domain.exception.PatientValidationException;
import com.globant.internal.oncocureassist.repository.PatientRepository;
import com.globant.internal.oncocureassist.repository.entity.Patient;
import com.globant.internal.oncocureassist.service.enricher.Enricher;
import com.globant.internal.oncocureassist.service.validator.ConstraintValidator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;
    private final ConstraintValidator<Patient> patientValidator;
    private final Enricher patientEnricher;


    public PatientController(PatientRepository patientRepository,
                             ConstraintValidator<Patient> patientValidator,
                             Enricher patientEnricher) {
        this.patientRepository = patientRepository;
        this.patientValidator = patientValidator;
        this.patientEnricher = patientEnricher;
    }


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Patient patient) {
        validatePatient(patient);
        patientEnricher.enrich(patient);
        patientRepository.save(patient);
    }


    @GetMapping
    public PageResponse find(@PageableDefault(size = 50) Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(notDeleted(null), pageable);
        return new PageResponse<>(patients.getTotalPages(), patients.getTotalElements(), patients.getContent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        return patientRepository.findOne(notDeleted(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Patient patient) {
        Optional<Patient> savedPatient = patientRepository.findOne(notDeleted(id));

        if (!savedPatient.isPresent()) {
            return ResponseEntity.notFound().build();
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
        });

        return ResponseEntity.noContent().build();
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientRepository.deleteById(id);
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
