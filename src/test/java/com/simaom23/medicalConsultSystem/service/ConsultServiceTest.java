package com.simaom23.medicalConsultSystem.service;

import com.simaom23.medicalConsultSystem.dto.consult.ConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.patient.PatientConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.symptom.SymptomResponseDTO;
import com.simaom23.medicalConsultSystem.entity.Consult;
import com.simaom23.medicalConsultSystem.entity.Doctor;
import com.simaom23.medicalConsultSystem.entity.Patient;
import com.simaom23.medicalConsultSystem.entity.Specialty;
import com.simaom23.medicalConsultSystem.entity.Symptom;
import com.simaom23.medicalConsultSystem.repository.ConsultRepository;
import com.simaom23.medicalConsultSystem.repository.PatientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ConsultRepository consultRepository;

    @InjectMocks
    private ConsultService consultService;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
    }

    @Test
    public void shouldReturnEmptyListsWhenPatientHasNoConsultsOrSymptoms() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(consultRepository.findByPatient(patient)).thenReturn(List.of());

        PatientConsultResponseDTO response = consultService.getPatientConsultsAndSymptoms(patient.getId());

        assertTrue(response.getConsults().isEmpty());
        assertTrue(response.getSymptoms().isEmpty());
    }

    // @Test
    // public void
    // shouldReturnCorrectConsultAndSymptomInformationWhenPatientHasMultipleConsultsAndSymptoms()
    // {
    // // Given
    // Patient patient = new Patient();
    // patient.setId(1L);

    // Consult consult1 = new Consult();
    // consult1.setId(1L);
    // consult1.setDoctor(new Doctor("Dr. Smith"));
    // consult1.setSpecialty(new Specialty("Cardiology"));
    // consult1.setSymptoms(List.of(new Symptom("Hypertension"), new Symptom("Chest
    // pain")));

    // Consult consult2 = new Consult();
    // consult2.setId(2L);
    // consult2.setDoctor(new Doctor("Dr. Johnson"));
    // consult2.setSpecialty(new Specialty("Neurology"));
    // consult2.setSymptoms(List.of(new Symptom("Seizures"), new
    // Symptom("Headache")));

    // when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
    // when(consultRepository.findByPatient(patient)).thenReturn(List.of(consult1,
    // consult2));

    // // When
    // PatientConsultResponseDTO response =
    // consultService.getPatientConsultsAndSymptoms(patient.getId());

    // // Then
    // List<ConsultResponseDTO> expectedConsultInfos = List.of(
    // new ConsultResponseDTO(1L, "Dr. Smith", "Cardiology"),
    // new ConsultResponseDTO(2L, "Dr. Johnson", "Neurology"));

    // Set<SymptomResponseDTO> expectedSymptomInfos = Set.of(
    // new SymptomResponseDTO(1L, "Hypertension"),
    // new SymptomResponseDTO(2L, "Chest pain"),
    // new SymptomResponseDTO(3L, "Seizures"),
    // new SymptomResponseDTO(4L, "Headache"));

    // assertEquals(expectedConsultInfos, response.getConsults());
    // assertEquals(expectedSymptomInfos, new HashSet<>(response.getSymptoms()));
    // }
}