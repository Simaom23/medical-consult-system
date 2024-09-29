package com.simaom23.medicalConsultSystem.service;

import com.simaom23.medicalConsultSystem.dto.consult.ConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.consult.CreateConsultDTO;
import com.simaom23.medicalConsultSystem.dto.patient.PatientConsultResponseDTO;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ConsultRepository consultRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Mock
    private SymptomRepository symptomRepository;

    @InjectMocks
    private ConsultService consultService;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
    }

    @Test
    public void shouldCreateConsultWithValidInputs() {
        // Given
        CreateConsultDTO consultDTO = new CreateConsultDTO();
        consultDTO.setDoctorId(1L);
        consultDTO.setPatientId(1L);
        consultDTO.setSpecialtyId(1L);
        consultDTO.setSymptomIds(List.of(1L, 2L));

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Smith");

        Patient patient = new Patient();
        patient.setId(1L);

        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("Cardiology");

        Symptom symptom1 = new Symptom();
        symptom1.setId(1L);
        symptom1.setDescription("Hypertension");

        Symptom symptom2 = new Symptom();
        symptom2.setId(2L);
        symptom2.setDescription("Chest pain");

        Consult consult = new Consult();
        consult.setId(1L);
        consult.setDoctor(doctor);
        consult.setPatient(patient);
        consult.setSpecialty(specialty);
        consult.setSymptoms(List.of(symptom1, symptom2));

        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(specialty));
        when(symptomRepository.findAllById(anyList())).thenReturn(List.of(symptom1, symptom2));
        when(consultRepository.save(any(Consult.class))).thenReturn(consult);

        // When
        ConsultResponseDTO response = consultService.createConsult(consultDTO);

        // Then
        assertEquals(1L, response.getConsultId());
        assertEquals("Dr. Smith", response.getDoctor());
        assertEquals("Cardiology", response.getSpecialty());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingConsultWithInvalidSymptomIds() {
        // Given
        CreateConsultDTO consultDTO = new CreateConsultDTO();
        consultDTO.setDoctorId(1L);
        consultDTO.setPatientId(1L);
        consultDTO.setSpecialtyId(1L);
        consultDTO.setSymptomIds(List.of(1L, 2L, 3L));

        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(new Doctor()));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(new Patient()));
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(new Specialty()));
        when(symptomRepository.findAllById(anyList())).thenReturn(List.of());

        // When
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            consultService.createConsult(consultDTO);
        });

        // Then
        assertTrue(exception.getMessage().contains("One or more symptom IDs not found."));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingConsultWithNonExistentDoctorId() {
        // Given
        CreateConsultDTO consultDTO = new CreateConsultDTO();
        consultDTO.setDoctorId(999L);
        consultDTO.setPatientId(1L);
        consultDTO.setSpecialtyId(1L);
        consultDTO.setSymptomIds(List.of(1L, 2L));

        when(doctorRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            consultService.createConsult(consultDTO);
        });

        // Then
        assertTrue(exception.getMessage().contains("Doctor with ID 999 not found."));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingConsultWithNonExistentPatientId() {
        // Given
        CreateConsultDTO consultDTO = new CreateConsultDTO();
        consultDTO.setDoctorId(1L);
        consultDTO.setPatientId(999L);
        consultDTO.setSpecialtyId(1L);
        consultDTO.setSymptomIds(List.of(1L, 2L));

        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(new Doctor()));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            consultService.createConsult(consultDTO);
        });

        // Then
        assertTrue(exception.getMessage().contains("Patient with ID 999 not found."));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingConsultWithNonExistentSpecialtyId() {
        // Given
        CreateConsultDTO consultDTO = new CreateConsultDTO();
        consultDTO.setDoctorId(1L);
        consultDTO.setPatientId(1L);
        consultDTO.setSpecialtyId(999L);
        consultDTO.setSymptomIds(List.of(1L, 2L));

        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(new Doctor()));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(new Patient()));
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            consultService.createConsult(consultDTO);
        });

        // Then
        assertTrue(exception.getMessage().contains("Specialty with ID 999 not found."));
    }

    @Test
    public void shouldHandlePatientWithMultipleConsultsAndSymptoms() {
        // Given
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);

        Doctor doctor1 = new Doctor();
        doctor1.setName("Dr. Smith");

        Doctor doctor2 = new Doctor();
        doctor2.setName("Dr. Johnson");

        Specialty specialty1 = new Specialty();
        specialty1.setName("Cardiology");

        Specialty specialty2 = new Specialty();
        specialty2.setName("Orthopedics");

        Symptom symptom1 = new Symptom();
        symptom1.setId(1L);
        symptom1.setDescription("Hypertension");

        Symptom symptom2 = new Symptom();
        symptom2.setId(2L);
        symptom2.setDescription("Chest pain");

        Symptom symptom3 = new Symptom();
        symptom3.setId(3L);
        symptom3.setDescription("Fracture");

        Consult consult1 = new Consult();
        consult1.setId(1L);
        consult1.setDoctor(doctor1);
        consult1.setSpecialty(specialty1);
        consult1.setSymptoms(List.of(symptom1, symptom2));

        Consult consult2 = new Consult();
        consult2.setId(2L);
        consult2.setDoctor(doctor2);
        consult2.setSpecialty(specialty2);
        consult2.setSymptoms(List.of(symptom3));

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(consultRepository.findByPatient(patient)).thenReturn(List.of(consult1, consult2));

        // When
        PatientConsultResponseDTO response = consultService.getPatientConsultsAndSymptoms(patientId);

        // Then
        assertEquals(2, response.getConsults().size());
        assertEquals(3, response.getSymptoms().size());

        ConsultResponseDTO consultInfo1 = response.getConsults().get(0);
        assertEquals(1L, consultInfo1.getConsultId());
        assertEquals("Dr. Smith", consultInfo1.getDoctor());
        assertEquals("Cardiology", consultInfo1.getSpecialty());

        ConsultResponseDTO consultInfo2 = response.getConsults().get(1);
        assertEquals(2L, consultInfo2.getConsultId());
        assertEquals("Dr. Johnson", consultInfo2.getDoctor());
        assertEquals("Orthopedics", consultInfo2.getSpecialty());

        // Check for symptom existence in the response
        assertTrue(response.getSymptoms().stream()
                .anyMatch(symptom -> symptom.getSymptomId() == 1L && symptom.getDescription().equals("Hypertension")));
        assertTrue(response.getSymptoms().stream()
                .anyMatch(symptom -> symptom.getSymptomId() == 2L && symptom.getDescription().equals("Chest pain")));
        assertTrue(response.getSymptoms().stream()
                .anyMatch(symptom -> symptom.getSymptomId() == 3L && symptom.getDescription().equals("Fracture")));
    }

    @Test
    public void shouldReturnEmptyListsWhenPatientHasNoConsultsOrSymptoms() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(consultRepository.findByPatient(patient)).thenReturn(List.of());

        PatientConsultResponseDTO response = consultService.getPatientConsultsAndSymptoms(patient.getId());

        assertTrue(response.getConsults().isEmpty());
        assertTrue(response.getSymptoms().isEmpty());
    }

    @Test
    public void shouldValidateInputParametersForGetPatientConsultsAndSymptoms() {
        // Given
        Long patientId = 1L;
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            consultService.getPatientConsultsAndSymptoms(patientId);
        });

        // Then
        assertTrue(exception.getMessage().contains("Patient with ID 1 not found."));
    }

}