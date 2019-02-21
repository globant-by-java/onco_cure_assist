package com.globant.internal.oncocureassist.service;

import com.globant.internal.oncocureassist.repository.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PatientService {

    void create(Patient patient);

    Page<Patient> find(Pageable pageable);

    Optional<Patient> findById(Long id);

    Optional<Patient> update(Long id, Patient patient);

    void delete(Long id);
}
