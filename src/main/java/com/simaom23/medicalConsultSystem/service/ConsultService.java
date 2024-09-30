package com.simaom23.medicalConsultSystem.service;

import com.simaom23.medicalConsultSystem.dto.consult.ConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.consult.CreateConsultDTO;
import com.simaom23.medicalConsultSystem.dto.patient.PatientConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.symptom.SymptomResponseDTO;
import com.simaom23.medicalConsultSystem.entity.Consult;
import com.simaom23.medicalConsultSystem.entity.Doctor;
import com.simaom23.medicalConsultSystem.entity.Patient;
import com.simaom23.medicalConsultSystem.entity.Specialty;
import com.simaom23.medicalConsultSystem.entity.Symptom;
import com.simaom23.medicalConsultSystem.repository.ConsultRepository;
import com.simaom23.medicalConsultSystem.repository.DoctorRepository;
import com.simaom23.medicalConsultSystem.repository.PatientRepository;
import com.simaom23.medicalConsultSystem.repository.SpecialtyRepository;
import com.simaom23.medicalConsultSystem.repository.SymptomRepository;
import com.simaom23.medicalConsultSystem.util.EntityMapper;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ConsultService {
        private static final Logger logger = LoggerFactory.getLogger(ConsultService.class);

        @Autowired
        private ConsultRepository consultRepository;

        @Autowired
        private DoctorRepository doctorRepository;

        @Autowired
        private PatientRepository patientRepository;

        @Autowired
        private SpecialtyRepository specialtyRepository;

        @Autowired
        private SymptomRepository symptomRepository;

        public ConsultResponseDTO createConsult(CreateConsultDTO consultDTO) {
                logger.info("Creating a new consult for Doctor ID: {}, Patient ID: {}, Specialty ID: {}",
                                consultDTO.getDoctorId(), consultDTO.getPatientId(), consultDTO.getSpecialtyId());

                Doctor doctor = doctorRepository.findById(consultDTO.getDoctorId())
                                .orElseThrow(() -> {
                                        logger.error("Doctor with ID {} not found.", consultDTO.getDoctorId());
                                        return new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                        String.format("Doctor with ID %d not found.",
                                                                        consultDTO.getDoctorId()));
                                });

                Patient patient = patientRepository.findById(consultDTO.getPatientId())
                                .orElseThrow(() -> {
                                        logger.error("Patient with ID {} not found.", consultDTO.getPatientId());
                                        return new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                        String.format("Patient with ID %d not found.",
                                                                        consultDTO.getPatientId()));
                                });

                Specialty specialty = specialtyRepository.findById(consultDTO.getSpecialtyId())
                                .orElseThrow(() -> {
                                        logger.error("Specialty with ID {} not found.", consultDTO.getSpecialtyId());
                                        return new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                        String.format("Specialty with ID %d not found.",
                                                                        consultDTO.getSpecialtyId()));
                                });

                Consult consult = new Consult();
                consult.setDoctor(doctor);
                consult.setPatient(patient);
                consult.setSpecialty(specialty);

                if (consultDTO.getSymptomIds() != null && !consultDTO.getSymptomIds().isEmpty()) {
                        logger.info("Adding symptoms to consult: {}", consultDTO.getSymptomIds());

                        List<Symptom> symptoms = symptomRepository.findAllById(consultDTO.getSymptomIds());
                        Set<Long> symptomIds = new HashSet<>(consultDTO.getSymptomIds());

                        if (symptoms.size() != symptomIds.size()) {
                                logger.error("One or more symptom IDs not found.");
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "One or more symptom IDs not found.");
                        }
                        consult.setSymptoms(symptoms);
                }

                Consult savedConsult = consultRepository.save(consult);
                logger.info("Consult created successfully with ID: {}", savedConsult.getId());

                ConsultResponseDTO consultResponseDTO = EntityMapper.toConsultResponseDTO(savedConsult);
                return consultResponseDTO;
        }

        public PatientConsultResponseDTO getPatientConsultsAndSymptoms(Long patientId) {
                logger.info("Fetching consults and symptoms for patient ID: {}", patientId);

                Patient patient = patientRepository.findById(patientId)
                                .orElseThrow(() -> {
                                        logger.error("Patient with ID {} not found.", patientId);
                                        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        String.format("Patient with ID %d not found.", patientId));
                                });

                List<Consult> consults = consultRepository.findByPatient(patient);

                List<ConsultResponseDTO> consultInfos = consults.stream().map(EntityMapper::toConsultResponseDTO)
                                .collect(Collectors.toList());

                Set<SymptomResponseDTO> symptomInfos = consults.stream()
                                .flatMap(consult -> consult.getSymptoms().stream())
                                .map(EntityMapper::toSymptomResponseDTO)
                                .collect(Collectors.toSet());

                PatientConsultResponseDTO response = new PatientConsultResponseDTO();
                response.setConsults(consultInfos);
                response.setSymptoms(new ArrayList<>(symptomInfos));

                logger.info("Successfully fetched consults and symptoms for patient ID: {}", patientId);

                return response;
        }
}
