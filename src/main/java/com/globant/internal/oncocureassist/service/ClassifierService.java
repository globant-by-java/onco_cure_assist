package com.globant.internal.oncocureassist.service;

import com.globant.internal.oncocureassist.domain.model.ClassifierModel;

import java.util.List;
import java.util.Map;

public interface ClassifierService {

    List<ClassifierModel> findAll();

    ClassifierModel create(Integer version);

    double classify(Map<String, String> patientModelMap, Integer version);
}
