package com.globant.internal.oncocureassist.domain.dictionary;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Gender {

    MALE(1), FEMALE(0);


    private static final List<Gender> GENDERS = Arrays.asList(Gender.values());

    private int number;


    Gender(int number) {
        this.number = number;
    }


    public static Gender of(Integer number) {
        return GENDERS.stream()
                .filter(gender -> Objects.equals(number, gender.number))
                .findFirst()
                .orElse(null);
    }


    public int getNumber() {
        return number;
    }


    public static List<Gender> getAll() {
        return GENDERS;
    }
}
