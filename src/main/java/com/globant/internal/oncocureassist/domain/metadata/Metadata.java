package com.globant.internal.oncocureassist.domain.metadata;

public class Metadata {

    private final String code;
    private final String name;


    public Metadata(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }
}
