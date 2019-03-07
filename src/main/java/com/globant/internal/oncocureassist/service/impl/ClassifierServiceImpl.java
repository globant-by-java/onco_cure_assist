package com.globant.internal.oncocureassist.service.impl;

import com.globant.internal.oncocureassist.classifier.DataClassifier;
import com.globant.internal.oncocureassist.classifier.DataClassifierCreator;
import com.globant.internal.oncocureassist.domain.model.ClassifierModel;
import com.globant.internal.oncocureassist.service.ClassifierService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class ClassifierServiceImpl implements ClassifierService {

    private final DataClassifier patientModelClassifier;
    private final DataClassifierCreator patientClassifierCreator;
    private final File wekaDir;


    ClassifierServiceImpl(DataClassifier patientModelClassifier,
                          DataClassifierCreator patientClassifierCreator,
                          File wekaDir) {
        this.patientModelClassifier = patientModelClassifier;
        this.patientClassifierCreator = patientClassifierCreator;
        this.wekaDir = wekaDir;
    }


    @Override
    public List<ClassifierModel> findAll() {
        File[] children = Optional.ofNullable(wekaDir.listFiles(File::isDirectory)).orElseGet(() -> new File[0]);

        return Arrays.stream(children)
                .map(ClassifierModel::new)
                .sorted(Comparator.comparing(ClassifierModel::getVersion))
                .collect(Collectors.toList());
    }


    @Override
    public ClassifierModel create(Integer version) {
        return patientClassifierCreator.create(version);
    }


    @Override
    public double classify(Map<String, String> patientModelMap, Integer version) {
        return patientModelClassifier.classify(patientModelMap, version);
    }
}
