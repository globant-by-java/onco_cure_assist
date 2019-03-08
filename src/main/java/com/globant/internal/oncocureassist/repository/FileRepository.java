package com.globant.internal.oncocureassist.repository;

import com.globant.internal.oncocureassist.domain.dictionary.FileTemplate;

import java.io.File;
import java.io.IOException;

public interface FileRepository {

    String getFileName(FileTemplate fileTemplate, Integer version);

    File createDirectory(FileTemplate fileTemplate, Integer path) throws IOException;

    void write(String data, FileTemplate fileTemplate, Integer version) throws IOException;
}
