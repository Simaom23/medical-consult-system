package com.simaom23.medicalConsultSystem.dto.specialty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopSpecialtyResponseDTO {
    private String specialtyName;
    private long numberOfPatients;
}
