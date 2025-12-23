package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private Boolean active;
    
    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers = new HashSet<>();
    
    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL)
    private Set<DiversityTarget> targets = new HashSet<>();
    
    public DiversityClassification() {}
    
    public DiversityClassification(String code, String description) {
        this.code = code != null ? code.toUpperCase() : null;
        this.description = description;
    }
    
    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) {
            this.active = true;
        }
        if (this.code != null) {
            this.code = this.code.toUpperCase();
        }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code != null ? code.toUpperCase() : null; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Set<Supplier> getSuppliers() { return suppliers; }
    public void setSuppliers(Set<Supplier> suppliers) { this.suppliers = suppliers; }
    public Set<DiversityTarget> getTargets() { return targets; }
    public void setTargets(Set<DiversityTarget> targets) { this.targets = targets; }
}