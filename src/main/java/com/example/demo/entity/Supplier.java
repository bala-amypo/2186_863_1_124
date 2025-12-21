package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String registrationNumber;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "supplier_classifications",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "classification_id")
    )
    private Set<DiversityClassification> diversityClassifications;

    @OneToMany(mappedBy = "supplier")
    private Set<PurchaseOrder> purchaseOrders;

    public Supplier() {}

    public Supplier(String name, String email, String registrationNumber) {
        this.name = name;
        this.email = email;
        this.registrationNumber = registrationNumber;
    }

    @PrePersist
    public void prePersist() {
        if (isActive == null) isActive = true;
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRegistrationNumber() { return registrationNumber; }
    public Boolean getIsActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Set<DiversityClassification> getDiversityClassifications() { return diversityClassifications; }
    public Set<PurchaseOrder> getPurchaseOrders() { return purchaseOrders; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public void setIsActive(Boolean active) { isActive = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setDiversityClassifications(Set<DiversityClassification> diversityClassifications) { this.diversityClassifications = diversityClassifications; }
    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) { this.purchaseOrders = purchaseOrders; }
}
