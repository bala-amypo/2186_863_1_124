package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int targetYear;

    @Min(0)
    @Max(100)
    private Double targetPercentage;

    private Boolean active;

    @ManyToOne
    private DiversityClassification classification;

    public DiversityTarget() {}

    @PrePersist
    public void prePersist() {
        if (active == null) {
            active = true;
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getTargetYear() { return targetYear; }
    public void setTargetYear(int targetYear) { this.targetYear = targetYear; }

    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public DiversityClassification getClassification() { return classification; }
    public void setClassification(DiversityClassification classification) {
        this.classification = classification;
    }
}
