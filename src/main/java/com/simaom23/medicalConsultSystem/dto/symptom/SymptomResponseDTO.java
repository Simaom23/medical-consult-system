package com.simaom23.medicalConsultSystem.dto.symptom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymptomResponseDTO {
    private Long symptomId;
    private String description;
}
