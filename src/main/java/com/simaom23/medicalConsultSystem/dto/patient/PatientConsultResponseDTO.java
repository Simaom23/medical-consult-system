package com.simaom23.medicalConsultSystem.dto.patient;

import java.util.List;

import com.simaom23.medicalConsultSystem.dto.consult.ConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.symptom.SymptomResponseDTO;

import lombok.Data;

@Data
public class PatientConsultResponseDTO {
    private List<ConsultResponseDTO> consults;
    private List<SymptomResponseDTO> symptoms;
}
