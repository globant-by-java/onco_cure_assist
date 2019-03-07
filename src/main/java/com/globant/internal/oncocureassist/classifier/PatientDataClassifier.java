package com.globant.internal.oncocureassist.classifier;

import static weka.core.converters.ConverterUtils.DataSource;

import com.globant.internal.oncocureassist.domain.exception.ClassifierExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.io.File;
import java.util.Enumeration;
import java.util.Map;

public class PatientDataClassifier implements DataClassifier {

    private static final Logger log = LoggerFactory.getLogger(PatientDataClassifier.class);

    private final String fileDirectory;
    private final String templateFileName;
    private final String classifierFileName;


    public PatientDataClassifier(String fileDirectory,
                                 String templateFileName,
                                 String classifierFileName) {
        this.fileDirectory = fileDirectory;
        this.templateFileName = templateFileName;
        this.classifierFileName = classifierFileName;
    }


    @Override
    public double classify(Map<String, String> model, Integer version) {
        try {
            return calculate(model, version);
        } catch (Exception exc) {
            log.error("Error while classify a patient with version " + version, exc);
            throw new ClassifierExecutionException();
        }
    }


    private double calculate(Map<String, String> model, Integer version) throws Exception {
        log.info("Calculate classId using classifier with version {}", version);
        Instance newData = makeInstance(model, version);
        String filename = fileDirectory + version + File.separator + classifierFileName;
        Classifier cls = (Classifier) SerializationHelper.read(filename);

        double classId = cls.classifyInstance(newData);
        log.info("Calculated value: {}", classId);

        return classId;
    }


    private Instance makeInstance(Map<String, String> model, Integer version) throws Exception {
        String filename = fileDirectory + version + File.separator + templateFileName;
        Instances template = DataSource.read(filename);
        Instance newData = new DenseInstance(template.numAttributes());
        newData.setDataset(template);

        Enumeration<Attribute> attributes = template.enumerateAttributes();
        while (attributes.hasMoreElements()) {
            Attribute attribute = attributes.nextElement();
            String value = model.get(attribute.name());
            if (value != null) {
                newData.setValue(attribute, value);
            }
        }
        template.setClassIndex(newData.numAttributes() - 1);

        return newData;
    }
}
