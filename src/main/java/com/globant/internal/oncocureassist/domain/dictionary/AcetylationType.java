package com.globant.internal.oncocureassist.domain.dictionary;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum AcetylationType {

    FAST("1"), SLOW("0");


    private static final List<AcetylationType> ACETYLATION_TYPES = Arrays.asList(AcetylationType.values());


    private final String code;


    AcetylationType(String code) {
        this.code = code;
    }


    public static List<AcetylationType> getAll() {
        return ACETYLATION_TYPES;
    }


    public static AcetylationType getByCode(String code) {
        return ACETYLATION_TYPES.stream()
                .filter(type -> Objects.equals(code, type.code))
                .findFirst()
                .orElse(null);
    }


    public static List<String> getAllCodes() {
        return ACETYLATION_TYPES.stream()
                .map(AcetylationType::getCode)
                .collect(Collectors.toList());
    }


    public String getCode() {
        return code;
    }
}
