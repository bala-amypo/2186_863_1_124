package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "spend_categories")
public class SpendCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean active;

    @OneToMany(mappedBy = "category")
    private Set<PurchaseOrder> purchaseOrders;

    public SpendCategory() {}

    public SpendCategory(String name) {
        this.name = name;
    }

    @PrePersist
    public void preSave() {
        if (active == null) active = true;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Boolean getActive() { return active; }
    public Set<PurchaseOrder> getPurchaseOrders() { return purchaseOrders; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setActive(Boolean active) { this.active = active; }
    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) { this.purchaseOrders = purchaseOrders; }
}
