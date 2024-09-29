package com.simaom23.medicalConsultSystem.dto.consult;

import lombok.Data;

@Data
public class ConsultResponseDTO {
    private Long consultId;
    private String doctor;
    private String specialty;
}
