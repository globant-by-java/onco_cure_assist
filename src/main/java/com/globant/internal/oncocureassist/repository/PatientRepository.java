package com.globant.internal.oncocureassist.repository;

import com.globant.internal.oncocureassist.repository.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Modifying
    @Query("UPDATE Patient p SET p.deleted = true WHERE p.id =:patientId")
    void deleteById(@Param("patientId") Long patientId);
}
