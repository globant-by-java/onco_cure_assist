package com.globant.internal.oncocureassist.domain.model;

import java.io.File;
import java.nio.file.Path;

public class ClassifierModel {

    private final Path path;
    private final int version;


    public ClassifierModel(File dir) {
        this.path = dir.toPath();
        this.version = Integer.parseInt(dir.getName());
    }


    public Path getPath() {
        return path;
    }


    public int getVersion() {
        return version;
    }
}
