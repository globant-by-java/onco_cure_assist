package com.globant.internal.oncocureassist.domain.dictionary;

public enum FileTemplate {

    CLASSIFIER("patient_classifier.model"),
    TEMPLATE("patient_template.arff"),
    DECISION_TREE("decision_tree.png"),
    EMPTY("");


    private String fileName;


    FileTemplate(String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
