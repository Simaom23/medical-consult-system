package com.simaom23.medicalConsultSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simaom23.medicalConsultSystem.dto.patient.PatientResponseDTO;
import com.simaom23.medicalConsultSystem.entity.Patient;
import com.simaom23.medicalConsultSystem.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Page<PatientResponseDTO> getAllPatients(Integer age, String name, int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        Page<Patient> patientsPage;

        if (age == null && name == null) {
            patientsPage = patientRepository.findAll(pagination);
        } else {
            patientsPage = patientRepository
                    .findByAgeOrName(age, name, pagination);
        }

        return patientsPage.map(patient -> {
            PatientResponseDTO dto = new PatientResponseDTO();
            dto.setId(patient.getId());
            dto.setName(patient.getName());
            dto.setAge(patient.getAge());
            return dto;
        });
    }
}
