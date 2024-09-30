package com.simaom23.medicalConsultSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simaom23.medicalConsultSystem.dto.patient.PatientResponseDTO;
import com.simaom23.medicalConsultSystem.entity.Patient;
import com.simaom23.medicalConsultSystem.repository.PatientRepository;

@Service
public class PatientService {

    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public Page<PatientResponseDTO> getAllPatients(Integer age, String name, int page, int size) {
        logger.info("Fetching all patients with filters - Age: {}, Name: {}, Page: {}, Size: {}", age, name, page,
                size);

        Pageable pagination = PageRequest.of(page, size);
        Page<Patient> patientsPage;

        if (age == null && name == null) {
            logger.info("No filters applied, retrieving all patients");
            patientsPage = patientRepository.findAll(pagination);
        } else {
            logger.info("Applying filters - Age: {}, Name: {}", age, name);
            patientsPage = patientRepository.findByAgeOrName(age, name, pagination);
        }

        logger.info("Retrieved {} patients on page {} with page size {}", patientsPage.getTotalElements(), page, size);

        return patientsPage.map(patient -> {
            PatientResponseDTO dto = new PatientResponseDTO();
            dto.setId(patient.getId());
            dto.setName(patient.getName());
            dto.setAge(patient.getAge());
            return dto;
        });
    }
}
