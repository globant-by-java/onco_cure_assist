package com.globant.internal.oncocureassist.domain.metadata;

import java.util.List;
import java.util.Map;

public class GeneticPredictorsMetadata {

    private final List<Metadata> acetylationTypes;
    private final Map<String, List<Metadata>> genotypes;


    public GeneticPredictorsMetadata(List<Metadata> acetylationTypes, Map<String, List<Metadata>> genotypes) {
        this.acetylationTypes = acetylationTypes;
        this.genotypes = genotypes;
    }


    public Map<String, List<Metadata>> getGenotypes() {
        return genotypes;
    }


    public List<Metadata> getAcetylationTypes() {
        return acetylationTypes;
    }
}
