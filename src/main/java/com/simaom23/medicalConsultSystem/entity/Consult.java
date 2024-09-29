package com.simaom23.medicalConsultSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.time.LocalDateTime;

@Data
@Entity
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @ManyToMany
    @JoinTable(name = "consult_symptom", joinColumns = @JoinColumn(name = "consult_id"), inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private List<Symptom> symptoms;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
