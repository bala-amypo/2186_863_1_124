package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private int targetYear;
    
    @Column(nullable = false)
    private Double targetPercentage;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id", nullable = false)
    private DiversityClassification classification;
    
    @Column(nullable = false)
    private Boolean active;
    
    public DiversityTarget() {}
    
    public DiversityTarget(int targetYear, DiversityClassification classification, Double targetPercentage) {
        this.targetYear = targetYear;
        this.classification = classification;
        this.targetPercentage = targetPercentage;
    }
    
    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) {
            this.active = true;
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getTargetYear() { return targetYear; }
    public void setTargetYear(int targetYear) { this.targetYear = targetYear; }
    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }
    public DiversityClassification getClassification() { return classification; }
    public void setClassification(DiversityClassification classification) { this.classification = classification; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}