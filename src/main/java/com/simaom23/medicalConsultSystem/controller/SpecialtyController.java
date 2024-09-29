package com.simaom23.medicalConsultSystem.controller;

import com.simaom23.medicalConsultSystem.dto.specialty.TopSpecialtyResponseDTO;
import com.simaom23.medicalConsultSystem.service.SpecialtyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
@Tag(name = "Specialties", description = "Operations related to medical specialties.")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping("/top")
    @Operation(summary = "Get top specialties", description = "Retrieves a list of the most requested specialties.")
    public List<TopSpecialtyResponseDTO> getTopSpecialties() {
        return specialtyService.getTopSpecialties();
    }
}
