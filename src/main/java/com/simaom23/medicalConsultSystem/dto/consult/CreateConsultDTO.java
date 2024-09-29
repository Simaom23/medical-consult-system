package com.simaom23.medicalConsultSystem.dto.consult;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateConsultDTO {
    private Long doctorId;
    private Long patientId;
    private Long specialtyId;
    private List<Long> symptomIds;
}
