package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Target year is required")
    private int targetYear;
    
    @NotNull(message = "Target percentage is required")
    @Min(value = 0, message = "Target percentage must be at least 0")
    @Max(value = 100, message = "Target percentage must not exceed 100")
    private Double targetPercentage;
    
    @NotNull(message = "Classification is required")
    @ManyToOne
    @JoinColumn(name = "classification_id")
    private DiversityClassification classification;
    
    private Boolean active;
    
    public DiversityTarget() {}
    
    public DiversityTarget(int targetYear, DiversityClassification classification, Double targetPercentage) {
        this.targetYear = targetYear;
        this.classification = classification;
        this.targetPercentage = targetPercentage;
    }
    
    @PrePersist
    @PreUpdate
    public void preSave() {
        if (this.active == null) {
            this.active = true;
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public int getTargetYear() {
        return targetYear;
    }
    
    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }
    
    public Double getTargetPercentage() {
        return targetPercentage;
    }
    
    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }
    
    public DiversityClassification getClassification() {
        return classification;
    }
    
    public void setClassification(DiversityClassification classification) {
        this.classification = classification;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
}