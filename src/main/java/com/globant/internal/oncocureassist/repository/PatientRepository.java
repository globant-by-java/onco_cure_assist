package com.globant.internal.oncocureassist.repository;

import com.globant.internal.oncocureassist.repository.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByCardNumber(String cardNumber);
}
