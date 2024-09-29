package com.simaom23.medicalConsultSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simaom23.medicalConsultSystem.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
