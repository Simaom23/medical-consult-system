package com.simaom23.medicalConsultSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaRepositories("com.simaom23.medicalConsultSystem.repository")
@EntityScan("com.simaom23.medicalConsultSystem.entity")
@OpenAPIDefinition(info = @Info(title = "Medical Consult System API", version = "3.0", description = "This API provides programmatic access to a medical consult system, allowing to retrieve and manipulate data related to patients, doctors, specialties, pathologies, and symptoms"))
public class MedicalConsultSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalConsultSystemApplication.class, args);
	}

}
