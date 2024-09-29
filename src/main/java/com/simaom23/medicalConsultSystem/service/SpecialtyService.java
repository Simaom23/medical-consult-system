package com.simaom23.medicalConsultSystem.service;

import com.simaom23.medicalConsultSystem.dto.specialty.TopSpecialtyResponseDTO;
import com.simaom23.medicalConsultSystem.repository.SpecialtyRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<TopSpecialtyResponseDTO> getTopSpecialties() {
        return specialtyRepository.findTopSpecialtiesWithMoreThanTwoPatients();
    }
}
