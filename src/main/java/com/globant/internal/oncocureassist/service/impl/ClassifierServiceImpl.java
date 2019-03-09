package com.globant.internal.oncocureassist.service.impl;

import com.globant.internal.oncocureassist.classifier.DataClassifier;
import com.globant.internal.oncocureassist.classifier.DataClassifierCreator;
import com.globant.internal.oncocureassist.domain.model.ClassifierModel;
import com.globant.internal.oncocureassist.repository.FileRepository;
import com.globant.internal.oncocureassist.service.ClassifierService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class ClassifierServiceImpl implements ClassifierService {

    private final DataClassifier patientModelClassifier;
    private final DataClassifierCreator patientClassifierCreator;
    private final FileRepository fileRepository;


    ClassifierServiceImpl(DataClassifier patientModelClassifier,
                          DataClassifierCreator patientClassifierCreator,
                          FileRepository fileRepository) {
        this.patientModelClassifier = patientModelClassifier;
        this.patientClassifierCreator = patientClassifierCreator;
        this.fileRepository = fileRepository;
    }


    @Override
    public List<ClassifierModel> findAll() {
        List<File> files = fileRepository.findAll();

        return files.stream()
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
