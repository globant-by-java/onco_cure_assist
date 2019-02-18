package com.globant.internal.oncocureassist.domain.metadata;

public class GlobalMetadata {

    private final PatientMetadata patientMetadata;
    private final GeneticPredictorsMetadata geneticPredictorsMetadata;


    public GlobalMetadata(PatientMetadata patientMetadata, GeneticPredictorsMetadata geneticPredictorsMetadata) {
        this.patientMetadata = patientMetadata;
        this.geneticPredictorsMetadata = geneticPredictorsMetadata;
    }


    public PatientMetadata getPatientMetadata() {
        return patientMetadata;
    }


    public GeneticPredictorsMetadata getGeneticPredictorsMetadata() {
        return geneticPredictorsMetadata;
    }
}
