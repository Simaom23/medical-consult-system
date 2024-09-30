package com.simaom23.medicalConsultSystem.service;

import com.simaom23.medicalConsultSystem.dto.specialty.TopSpecialtyResponseDTO;
import com.simaom23.medicalConsultSystem.repository.SpecialtyRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {

    private static final Logger logger = LoggerFactory.getLogger(SpecialtyService.class);

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<TopSpecialtyResponseDTO> getTopSpecialties() {
        logger.info("Fetching top specialties with more than two patients.");

        List<TopSpecialtyResponseDTO> topSpecialties = specialtyRepository.findTopSpecialtiesWithMoreThanTwoPatients();

        logger.info("Retrieved {} top specialties.", topSpecialties.size());

        return topSpecialties;
    }
}
