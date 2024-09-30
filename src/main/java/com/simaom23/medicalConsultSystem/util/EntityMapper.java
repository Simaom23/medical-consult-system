package com.simaom23.medicalConsultSystem.util;

import com.simaom23.medicalConsultSystem.dto.consult.ConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.patient.PatientResponseDTO;
import com.simaom23.medicalConsultSystem.dto.symptom.SymptomResponseDTO;
import com.simaom23.medicalConsultSystem.entity.Consult;
import com.simaom23.medicalConsultSystem.entity.Patient;
import com.simaom23.medicalConsultSystem.entity.Symptom;

public class EntityMapper {

    public static ConsultResponseDTO toConsultResponseDTO(Consult consult) {
        ConsultResponseDTO dto = new ConsultResponseDTO();
        dto.setConsultId(consult.getId());
        dto.setDoctor(consult.getDoctor().getName());
        dto.setSpecialty(consult.getSpecialty().getName());
        return dto;
    }

    public static SymptomResponseDTO toSymptomResponseDTO(Symptom symptom) {
        SymptomResponseDTO dto = new SymptomResponseDTO();
        dto.setSymptomId(symptom.getId());
        dto.setDescription(symptom.getDescription());
        return dto;
    }

    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        return dto;
    }
}
