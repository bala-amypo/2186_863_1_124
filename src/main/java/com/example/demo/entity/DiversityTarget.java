package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int targetYear;

    private Double targetPercentage;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    private DiversityClassification classification;

    public DiversityTarget() {}

    public DiversityTarget(int targetYear, DiversityClassification classification, Double targetPercentage) {
        this.targetYear = targetYear;
        this.classification = classification;
        this.targetPercentage = targetPercentage;
    }

    @PrePersist
    public void preSave() {
        if (active == null) active = true;
    }

    public Long getId() { return id; }
    public int getTargetYear() { return targetYear; }
    public Double getTargetPercentage() { return targetPercentage; }
    public Boolean getActive() { return active; }
    public DiversityClassification getClassification() { return classification; }

    public void setId(Long id) { this.id = id; }
    public void setTargetYear(int targetYear) { this.targetYear = targetYear; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }
    public void setActive(Boolean active) { this.active = active; }
    public void setClassification(DiversityClassification classification) { this.classification = classification; }
}
