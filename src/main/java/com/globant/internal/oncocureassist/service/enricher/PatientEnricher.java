package com.globant.internal.oncocureassist.service.enricher;

import com.globant.internal.oncocureassist.repository.entity.Patient;
import com.globant.internal.oncocureassist.repository.entity.Treatment;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class PatientEnricher implements Enricher {

    @Override
    public void enrich(Patient patient) {
        Optional.ofNullable(patient.getTreatment())
                .map(Treatment::getSurgeryDate)
                .map(surgeryDate -> ChronoUnit.MONTHS.between(patient.getContactDate(), surgeryDate))
                .ifPresent(sm -> {
                    double survivalMonth = sm.doubleValue();
                    patient.setSurvivalMonth(survivalMonth);
                    patient.setClassId(survivalMonth < 36 ? 0 : 1);
                });

        Optional.ofNullable(patient.getDiagnostics())
                .ifPresent(diagnostics -> {
                    String tnm = diagnostics.getTnm();
                    diagnostics.setT(String.valueOf(tnm.charAt(tnm.indexOf("T") + 1)));
                    diagnostics.setM(String.valueOf(tnm.charAt(tnm.indexOf("M") + 1)));
                    diagnostics.setN(String.valueOf(tnm.charAt(tnm.indexOf("N") + 1)));
                });
    }
}
