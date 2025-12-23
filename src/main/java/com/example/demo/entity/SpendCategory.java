package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spend_categories")
public class SpendCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Boolean active;

    @OneToMany(mappedBy = "category")
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

    public SpendCategory() {}

    public SpendCategory(String name) {
        this.name = name;
    }

    @PrePersist
    public void preSave() {
        if (this.active == null) {
            this.active = true;
        }
    }

    // Getters and setters

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
