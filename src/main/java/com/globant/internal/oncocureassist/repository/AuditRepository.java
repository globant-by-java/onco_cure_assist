package com.globant.internal.oncocureassist.repository;

import com.globant.internal.oncocureassist.repository.entity.Audit;
import org.springframework.data.repository.CrudRepository;

public interface AuditRepository extends CrudRepository<Audit, Long> {
}
