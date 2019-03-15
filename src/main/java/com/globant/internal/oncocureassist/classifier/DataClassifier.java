package com.globant.internal.oncocureassist.classifier;

import java.util.Map;

public interface DataClassifier {

    double classify(Map<String, String> model, Integer version);
}
