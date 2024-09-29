package com.simaom23.medicalConsultSystem.controller;

import org.springframework.web.bind.annotation.*;

import com.simaom23.medicalConsultSystem.dto.consult.ConsultResponseDTO;
import com.simaom23.medicalConsultSystem.dto.consult.CreateConsultDTO;
import com.simaom23.medicalConsultSystem.dto.patient.PatientConsultResponseDTO;
import com.simaom23.medicalConsultSystem.service.ConsultService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/consults")
@Tag(name = "Consults", description = "Operations related to consults.")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @PostMapping
    @Operation(summary = "Create a new consult", description = "Creates a new consult for a patient.")
    public ResponseEntity<ConsultResponseDTO> createConsult(@RequestBody CreateConsultDTO consultDTO) {
        ConsultResponseDTO consult = consultService.createConsult(consultDTO);
        return ResponseEntity.ok(consult);
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get patient consults and symptoms", description = "Retrieves a list of consults and associated symptoms for a given patient.")
    public ResponseEntity<PatientConsultResponseDTO> getPatientConsultsAndSymptoms(@PathVariable Long patientId) {
        PatientConsultResponseDTO response = consultService.getPatientConsultsAndSymptoms(patientId);
        return ResponseEntity.ok(response);
    }

}
