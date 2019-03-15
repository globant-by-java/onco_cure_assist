package com.globant.internal.oncocureassist.endpoint;

import com.globant.internal.oncocureassist.repository.entity.Patient;
import com.globant.internal.oncocureassist.service.ClassifierService;
import com.globant.internal.oncocureassist.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final ClassifierService classifierService;


    public PatientController(PatientService patientService,
                             ClassifierService classifierService) {
        this.patientService = patientService;
        this.classifierService = classifierService;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void create(@RequestBody Patient patient) {
        patientService.createOrUpdate(patient);
    }


    @GetMapping
    public PageResponse find(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = "50") Integer size) {
        Page<Patient> patients = patientService.find(PageRequest.of(page, size));
        return new PageResponse<>(patients.getTotalPages(), patients.getTotalElements(), patients.getContent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        return patientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }


    @PostMapping("/version/{version}/classify")
    public ResponseEntity<Integer> classify(@PathVariable Integer version, @RequestBody Patient patient) {
        return classifierService.findAll().stream()
                .filter(model -> model.getVersion() == version)
                .findFirst()
                .map(model -> patientService.classifyPatient(patient, version))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
