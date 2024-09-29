package com.simaom23.medicalConsultSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.simaom23.medicalConsultSystem.dto.patient.PatientResponseDTO;
import com.simaom23.medicalConsultSystem.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patients", description = "Operations related to patients.")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    @Operation(summary = "Get all patients (paginated)", description = "Retrieves a page of patients with optional filtering by age and name.")
    public Page<PatientResponseDTO> getAllPatients(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return patientService.getAllPatients(age, name, page, size);
    }
}
