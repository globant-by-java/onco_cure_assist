package com.globant.internal.oncocureassist.domain.model;

import com.globant.internal.oncocureassist.domain.dictionary.FileTemplate;

import java.io.File;

public class ClassifierModel {

    private final String decisionTreePath;
    private final int version;


    public ClassifierModel(File classifierDir) {
        this.decisionTreePath = classifierDir.getAbsolutePath() + File.separator + FileTemplate.DECISION_TREE.getFileName();
        this.version = Integer.parseInt(classifierDir.getName());
    }


    public String getDecisionTreePath() {
        return decisionTreePath;
    }


    public int getVersion() {
        return version;
    }
}
