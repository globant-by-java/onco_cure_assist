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
                    diagnostics.setT(substringBetween(tnm, "T", "N"));
                    diagnostics.setN(substringBetween(tnm, "N", "M"));
                    diagnostics.setM(tnm.substring(tnm.indexOf("M") + 1));
                });
    }


    private String substringBetween(String tnm, String t, String n) {
        return tnm.substring(tnm.indexOf(t) + 1, tnm.indexOf(n));
    }
}
