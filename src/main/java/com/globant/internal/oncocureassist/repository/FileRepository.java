package com.globant.internal.oncocureassist.repository;

import com.globant.internal.oncocureassist.domain.dictionary.FileTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileRepository {

    String getFileName(FileTemplate fileTemplate, Integer version);

    File createDirectory(FileTemplate fileTemplate, Integer path) throws IOException;

    void save(String data, FileTemplate fileTemplate, Integer version) throws IOException;

    List<File> findAll();
}
