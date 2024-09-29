package com.simaom23.medicalConsultSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simaom23.medicalConsultSystem.entity.Symptom;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}
