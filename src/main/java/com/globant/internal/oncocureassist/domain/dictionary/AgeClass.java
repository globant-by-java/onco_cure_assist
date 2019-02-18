package com.globant.internal.oncocureassist.domain.dictionary;

import org.springframework.data.domain.Range;
import org.springframework.data.domain.Range.Bound;

import java.util.Arrays;
import java.util.List;

public enum AgeClass {

    ZERO("0", Range.of(Bound.inclusive(0), Bound.exclusive(10))),
    ONE("1", Range.of(Bound.inclusive(10), Bound.exclusive(20))),
    TWO("2", Range.of(Bound.inclusive(20), Bound.exclusive(30))),
    THREE("3", Range.of(Bound.inclusive(30), Bound.exclusive(40))),
    FOUR("4", Range.of(Bound.inclusive(40), Bound.exclusive(50))),
    FIVE("5", Range.of(Bound.inclusive(50), Bound.exclusive(60))),
    SIX("6", Range.of(Bound.inclusive(60), Bound.exclusive(70))),
    SEVEN("7", Range.of(Bound.inclusive(70), Bound.exclusive(80))),
    EIGHT("8", Range.from(Bound.inclusive(80)).to(Bound.unbounded()));

    private static final List<AgeClass> AGE_CLASSES = Arrays.asList(AgeClass.values());

    private String code;
    private Range<Integer> range;


    AgeClass(String code, Range<Integer> range) {
        this.code = code;
        this.range = range;
    }


    public static AgeClass getByAge(Integer age) {
        return AGE_CLASSES.stream()
                .filter(ageClass -> age != null)
                .filter(ageClass -> ageClass.getRange().contains(age))
                .findFirst()
                .orElse(null);
    }


    public static List<AgeClass> getAll() {
        return AGE_CLASSES;
    }


    public String getCode() {
        return code;
    }


    public Range<Integer> getRange() {
        return range;
    }
}
