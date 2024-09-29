package com.simaom23.medicalConsultSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.simaom23.medicalConsultSystem.entity.Consult;
import com.simaom23.medicalConsultSystem.entity.Patient;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    List<Consult> findByPatient(Patient patient);
}
