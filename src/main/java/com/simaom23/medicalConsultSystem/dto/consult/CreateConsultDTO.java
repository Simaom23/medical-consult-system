package com.simaom23.medicalConsultSystem.dto.consult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateConsultDTO {
    private Long doctorId;
    private Long patientId;
    private Long specialtyId;
    private List<Long> symptomIds;
}
