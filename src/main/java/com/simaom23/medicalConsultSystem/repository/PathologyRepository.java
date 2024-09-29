package com.simaom23.medicalConsultSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simaom23.medicalConsultSystem.entity.Pathology;

public interface PathologyRepository extends JpaRepository<Pathology, Long> {
}
