package com.globant.internal.oncocureassist.endpoint;

import com.globant.internal.oncocureassist.repository.PatientRepository;
import com.globant.internal.oncocureassist.repository.entity.Patient;
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

import java.util.Optional;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private final PatientRepository patientRepository;


    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Patient patient) {
        patientRepository.save(patient);
    }


    @GetMapping
    public PageResponse find(@PageableDefault(size = 50) Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(pageable);
        return new PageResponse<>(patients.getTotalPages(), patients.getTotalElements(), patients.getContent());
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Patient patient) {
        Optional<Patient> savedPatient = patientRepository.findById(id);

        if (!savedPatient.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        savedPatient.ifPresent(p -> {
            patient.setId(p.getId());

            Optional.ofNullable(p.getTreatment())
                    .ifPresent(obj -> patient.getTreatment().setId(obj.getId()));

            Optional.ofNullable(p.getDiagnostics())
                    .ifPresent(obj -> patient.getDiagnostics().setId(obj.getId()));

            Optional.ofNullable(p.getGeneticPredictors())
                    .ifPresent(obj -> patient.getGeneticPredictors().setId(obj.getId()));


            patientRepository.save(patient);
        });

        return ResponseEntity.noContent().build();
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
}
