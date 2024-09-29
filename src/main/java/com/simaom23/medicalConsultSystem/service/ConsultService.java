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

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConsultService {

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

        public Consult createConsult(CreateConsultDTO consultDTO) {
                Doctor doctor = doctorRepository.findById(consultDTO.getDoctorId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                String.format("Doctor with ID %d not found.",
                                                                consultDTO.getDoctorId())));

                Patient patient = patientRepository.findById(consultDTO.getPatientId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                String.format("Patient with ID %d not found.",
                                                                consultDTO.getPatientId())));

                Specialty specialty = specialtyRepository.findById(consultDTO.getSpecialtyId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                String.format("Specialty with ID %d not found.",
                                                                consultDTO.getSpecialtyId())));

                Consult consult = new Consult();
                consult.setDoctor(doctor);
                consult.setPatient(patient);
                consult.setSpecialty(specialty);

                if (consultDTO.getSymptomIds() != null && !consultDTO.getSymptomIds().isEmpty()) {
                        List<Symptom> symptoms = symptomRepository.findAllById(consultDTO.getSymptomIds());
                        Set<Long> symptomIds = new HashSet<>(consultDTO.getSymptomIds());

                        if (symptoms.size() != symptomIds.size()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "One or more symptom IDs not found.");
                        }
                        consult.setSymptoms(symptoms);
                }

                return consultRepository.save(consult);
        }

        public PatientConsultResponseDTO getPatientConsultsAndSymptoms(Long patientId) {
                Patient patient = patientRepository.findById(patientId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                String.format("Patient with ID %d not found.", patientId)));

                List<Consult> consults = consultRepository.findByPatient(patient);

                List<ConsultResponseDTO> consultInfos = consults.stream().map(consult -> {
                        ConsultResponseDTO info = new ConsultResponseDTO();
                        info.setConsultId(consult.getId());
                        info.setDoctor(consult.getDoctor().getName());
                        info.setSpecialty(consult.getSpecialty().getName());
                        return info;
                }).collect(Collectors.toList());

                Set<SymptomResponseDTO> symptomInfos = consults.stream()
                                .flatMap(consult -> consult.getSymptoms().stream())
                                .map(symptom -> {
                                        SymptomResponseDTO info = new SymptomResponseDTO();
                                        info.setSymptomId(symptom.getId());
                                        info.setDescription(symptom.getDescription());
                                        return info;
                                })
                                .collect(Collectors.toSet());

                PatientConsultResponseDTO response = new PatientConsultResponseDTO();
                response.setConsults(consultInfos);
                response.setSymptoms(new ArrayList<>(symptomInfos));

                return response;
        }
}
