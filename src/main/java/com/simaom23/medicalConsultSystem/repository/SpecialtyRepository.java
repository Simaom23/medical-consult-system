package com.simaom23.medicalConsultSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simaom23.medicalConsultSystem.dto.specialty.TopSpecialtyResponseDTO;
import com.simaom23.medicalConsultSystem.entity.Specialty;
import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query("SELECT new com.simaom23.medicalConsultSystem.dto.specialty.TopSpecialtyResponseDTO(s.name, COUNT(DISTINCT c.patient.id) as numberOfPatients)  "
            +
            "FROM Consult c " +
            "INNER JOIN Specialty s ON c.specialty = s " +
            "GROUP BY s.id, s.name " +
            "HAVING COUNT(DISTINCT c.patient.id) > 2 " +
            "ORDER BY numberOfPatients DESC")
    List<TopSpecialtyResponseDTO> findTopSpecialtiesWithMoreThanTwoPatients();

}
