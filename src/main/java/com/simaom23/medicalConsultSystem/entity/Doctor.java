package com.simaom23.medicalConsultSystem.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "specialty_id", unique = true)
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor")
    private List<Consult> consults;

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
