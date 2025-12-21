package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    private Boolean active;

    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers;

    @OneToMany(mappedBy = "classification")
    private Set<DiversityTarget> targets;

    public DiversityClassification() {}

    public DiversityClassification(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        if (active == null) active = true;
        if (code != null) code = code.toUpperCase();
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public Boolean getActive() { return active; }
    public Set<Supplier> getSuppliers() { return suppliers; }
    public Set<DiversityTarget> getTargets() { return targets; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(Boolean active) { this.active = active; }
    public void setSuppliers(Set<Supplier> suppliers) { this.suppliers = suppliers; }
    public void setTargets(Set<DiversityTarget> targets) { this.targets = targets; }
}
