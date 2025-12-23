package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Code is required")
    private String code;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    private Boolean active;
    
    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers = new HashSet<>();
    
    @OneToMany(mappedBy = "classification")
    private Set<DiversityTarget> targets = new HashSet<>();
    
    public DiversityClassification() {}
    
    public DiversityClassification(String code, String description) {
        this.code = code != null ? code.toUpperCase() : null;
        this.description = description;
    }
    
    @PrePersist
    @PreUpdate
    public void preSave() {
        if (this.active == null) {
            this.active = true;
        }
        if (this.code != null) {
            this.code = this.code.toUpperCase();
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code != null ? code.toUpperCase() : null;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public Set<Supplier> getSuppliers() {
        return suppliers;
    }
    
    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    
    public Set<DiversityTarget> getTargets() {
        return targets;
    }
    
    public void setTargets(Set<DiversityTarget> targets) {
        this.targets = targets;
    }
}