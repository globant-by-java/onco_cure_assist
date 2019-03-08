package com.globant.internal.oncocureassist.repository;

import com.globant.internal.oncocureassist.domain.dictionary.FileTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WekaFileRepository implements FileRepository {

    private final File wekaDir;


    public WekaFileRepository(File wekaDir) {
        this.wekaDir = wekaDir;
    }


    @Override
    public String getFileName(FileTemplate fileTemplate, Integer version) {
        return wekaDir.getAbsolutePath() + File.separator + version + File.separator + fileTemplate.getFileName();
    }


    @Override
    public File createDirectory(FileTemplate fileTemplate, Integer version) throws IOException {
        String dir = getFileName(fileTemplate, version);
        return Files.createDirectories(Paths.get(dir)).toFile();
    }


    @Override
    public void write(String data, FileTemplate fileTemplate, Integer version) throws IOException {
        String fileName = getFileName(fileTemplate, version);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(data);
        }
    }
}
