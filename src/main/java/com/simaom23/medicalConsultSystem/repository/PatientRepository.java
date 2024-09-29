package com.simaom23.medicalConsultSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.simaom23.medicalConsultSystem.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByAgeOrName(Integer age, String name, Pageable pageable);
}
