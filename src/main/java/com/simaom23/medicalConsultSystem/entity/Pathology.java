package com.simaom23.medicalConsultSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.time.LocalDateTime;

@Data
@Entity
public class Pathology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "pathologies")
    private Set<Patient> patients;

    @ManyToMany
    @JoinTable(name = "Pathology_Symptom", joinColumns = @JoinColumn(name = "pathology_id"), inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private Set<Symptom> symptoms;

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
