package com.globant.internal.oncocureassist.classifier;

import com.globant.internal.oncocureassist.domain.exception.ClassifierCreationException;
import com.globant.internal.oncocureassist.domain.model.ClassifierModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PatientDataClassifierCreator implements DataClassifierCreator {

    private static final Logger log = LoggerFactory.getLogger(PatientDataClassifierCreator.class);

    private final Classifier classifier;
    private final InstanceQuery instanceQuery;
    private final Filter filter;
    private final File wekaDir;
    private final String templateFileName;
    private final String classifierFileName;


    public PatientDataClassifierCreator(Classifier classifier,
                                        InstanceQuery instanceQuery,
                                        Filter filter,
                                        File wekaDir,
                                        String templateFileName,
                                        String classifierFileName) {
        this.classifier = classifier;
        this.instanceQuery = instanceQuery;
        this.filter = filter;
        this.wekaDir = wekaDir;
        this.templateFileName = templateFileName;
        this.classifierFileName = classifierFileName;
    }


    public ClassifierModel create(Integer version) {
        String classifierDirPath = wekaDir.getAbsolutePath() + File.separator + version;
        try {
            log.info("Create directory for classifier with version {}", version);
            Path directory = createDirectory(classifierDirPath);
            return createClassifier(directory.toFile());
        } catch (Exception exc) {
            log.error("Error while creating a new classifier", exc);
            new File(classifierDirPath).delete();
            throw new ClassifierCreationException();
        }
    }


    private Path createDirectory(String classifierDirPath) throws IOException {
        return Files.createDirectories(Paths.get(classifierDirPath));
    }


    private ClassifierModel createClassifier(File directory) throws Exception {
        Instances data = getData();
        log.info("Train new classifier on {} data", data.size());
        classifier.buildClassifier(data);

        saveClassifier(directory);
        saveTemplate(directory, data);

        return new ClassifierModel(directory);
    }


    private Instances getData() throws Exception {
        Instances data = instanceQuery.retrieveInstances();
        data.setClassIndex(data.numAttributes() - 1);
        filter.setInputFormat(data);

        return Filter.useFilter(data, filter);
    }


    private void saveClassifier(File directory) throws Exception {
        String classifierFile = directory.getAbsolutePath() + File.separator + classifierFileName;
        log.info("Create classifier {}", classifierFile);
        SerializationHelper.write(classifierFile, classifier);
    }


    private void saveTemplate(File directory, Instances data) throws Exception {
        String templateFile = directory.getAbsolutePath() + File.separator + templateFileName;
        data.clear();
        log.info("Create template {}", templateFile);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(templateFile))) {
            writer.write(data.toString());
        }
    }
}
