package com.globant.internal.oncocureassist.domain.metadata;

import java.util.List;

public class PatientMetadata {

    private final List<Metadata> genders;
    private final List<Metadata> ageClasses;


    public PatientMetadata(List<Metadata> genders, List<Metadata> ageClasses) {
        this.genders = genders;
        this.ageClasses = ageClasses;
    }


    public List<Metadata> getGenders() {
        return genders;
    }


    public List<Metadata> getAgeClasses() {
        return ageClasses;
    }
}
