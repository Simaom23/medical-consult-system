package com.simaom23.medicalConsultSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.time.LocalDateTime;

@Data
@Entity
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "symptoms")
    private Set<Pathology> pathologies;

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
