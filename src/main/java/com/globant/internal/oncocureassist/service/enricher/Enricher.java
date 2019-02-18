package com.globant.internal.oncocureassist.service.enricher;

import com.globant.internal.oncocureassist.repository.entity.Patient;

public interface Enricher {

    void enrich(Patient patient);
}
