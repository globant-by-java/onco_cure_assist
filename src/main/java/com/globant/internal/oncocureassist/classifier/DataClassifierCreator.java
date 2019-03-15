package com.globant.internal.oncocureassist.classifier;

import com.globant.internal.oncocureassist.domain.model.ClassifierModel;

public interface DataClassifierCreator {

    ClassifierModel create(Integer version);
}
