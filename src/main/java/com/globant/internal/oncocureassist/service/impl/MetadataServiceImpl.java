package com.globant.internal.oncocureassist.service.impl;

import com.globant.internal.oncocureassist.domain.metadata.GeneticPredictorsMetadata;
import com.globant.internal.oncocureassist.domain.metadata.GlobalMetadata;
import com.globant.internal.oncocureassist.domain.metadata.Metadata;
import com.globant.internal.oncocureassist.domain.metadata.PatientMetadata;
import com.globant.internal.oncocureassist.domain.dictionary.AcetylationType;
import com.globant.internal.oncocureassist.domain.dictionary.AgeClass;
import com.globant.internal.oncocureassist.domain.dictionary.Gender;
import com.globant.internal.oncocureassist.domain.dictionary.Genotype;
import com.globant.internal.oncocureassist.service.MetadataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class MetadataServiceImpl implements MetadataService {

    private static final List<Metadata> EMPTY_METADATA = Collections.singletonList(new Metadata(null, ""));


    @Override
    public GlobalMetadata provideMetadata() {
        List<Metadata> genders = Gender.getAll().stream()
                .map(gender -> new Metadata(String.valueOf(gender.getNumber()), gender.name()))
                .collect(Collectors.toList());

        List<Metadata> ageClasses = AgeClass.getAll().stream()
                .map(ageClass -> new Metadata(ageClass.getCode(), ageClass.getRange().toString()))
                .collect(Collectors.toList());

        List<Metadata> acetylationTypes = new ArrayList<>(EMPTY_METADATA);
        acetylationTypes.addAll(AcetylationType.getAll().stream()
                .map(type -> new Metadata(type.getCode(), type.name()))
                .collect(Collectors.toList()));

        Map<String, List<Metadata>> genotypeMetadata = Arrays.stream(Genotype.values())
                .collect(Collectors.toMap(Enum::name, this::genotypeToMetadata));

        return new GlobalMetadata(new PatientMetadata(genders, ageClasses),
                new GeneticPredictorsMetadata(acetylationTypes, genotypeMetadata));
    }


    private List<Metadata> genotypeToMetadata(Genotype genotype) {
        List<Metadata> genotypeMetadata = new ArrayList<>(EMPTY_METADATA);
        genotypeMetadata.addAll(genotype.getValues().stream()
                .map(value -> new Metadata(value.toUpperCase(), value))
                .collect(Collectors.toList()));

        return genotypeMetadata;
    }
}
