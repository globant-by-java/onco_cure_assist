package com.globant.internal.oncocureassist.service;

import com.globant.internal.oncocureassist.domain.dictionary.AuditAction;
import com.globant.internal.oncocureassist.repository.entity.Patient;

public interface AuditService {

    void add(Patient patient, AuditAction action);
}
