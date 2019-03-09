package com.globant.internal.oncocureassist.classifier;

import com.globant.internal.oncocureassist.domain.dictionary.FileTemplate;
import com.globant.internal.oncocureassist.domain.exception.ClassifierCreationException;
import com.globant.internal.oncocureassist.domain.model.ClassifierModel;
import com.globant.internal.oncocureassist.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PatientDataClassifierCreator implements DataClassifierCreator {

    private static final Logger log = LoggerFactory.getLogger(PatientDataClassifierCreator.class);

    private final Classifier classifier;
    private final InstanceQuery instanceQuery;
    private final Filter filter;
    private final FileRepository wekaFileRepository;


    public PatientDataClassifierCreator(Classifier classifier,
                                        InstanceQuery instanceQuery,
                                        Filter filter,
                                        FileRepository wekaFileRepository) {
        this.classifier = classifier;
        this.instanceQuery = instanceQuery;
        this.filter = filter;
        this.wekaFileRepository = wekaFileRepository;
    }


    public ClassifierModel create(Integer version) {
        File classifierDir = null;
        try {
            classifierDir = wekaFileRepository.createDirectory(FileTemplate.EMPTY, version);
            log.info("Create directory for classifier with version {}", version);
            return createClassifier(version, classifierDir);
        } catch (Exception exc) {
            log.error("Error while creating a new classifier", exc);
            Optional.ofNullable(classifierDir).ifPresent(File::delete);
            throw new ClassifierCreationException();
        }
    }


    private ClassifierModel createClassifier(Integer version, File classifierDir) throws Exception {
        Instances data = getData();
        log.info("Train new classifier on {} data", data.size());
        classifier.buildClassifier(data);

        saveClassifier(version);
        saveTemplate(data, version);

        return new ClassifierModel(classifierDir);
    }


    private Instances getData() throws Exception {
        Instances data = instanceQuery.retrieveInstances();
        data.setClassIndex(data.numAttributes() - 1);
        filter.setInputFormat(data);

        return Filter.useFilter(data, filter);
    }


    private void saveClassifier(Integer version) throws Exception {
        String classifierFile = wekaFileRepository.getFileName(FileTemplate.CLASSIFIER, version);
        log.info("Create classifier {}", classifierFile);
        SerializationHelper.write(classifierFile, classifier);
    }


    private void saveTemplate(Instances data, Integer version) throws IOException {
        data.clear();
        wekaFileRepository.save(data.toString(), FileTemplate.TEMPLATE, version);
    }
}
