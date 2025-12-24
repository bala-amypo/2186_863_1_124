package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String code;

    private String description;

    private Boolean active;

    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers;

    @OneToMany(mappedBy = "classification")
    private Set<DiversityTarget> targets;

    public DiversityClassification() {}

    @PrePersist
    public void prePersist() {
        if (active == null) {
            active = true;
        }
        if (code != null) {
            code = code.toUpperCase();
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Set<Supplier> getSuppliers() { return suppliers; }
    public void setSuppliers(Set<Supplier> suppliers) { this.suppliers = suppliers; }

    public Set<DiversityTarget> getTargets() { return targets; }
    public void setTargets(Set<DiversityTarget> targets) { this.targets = targets; }
}
