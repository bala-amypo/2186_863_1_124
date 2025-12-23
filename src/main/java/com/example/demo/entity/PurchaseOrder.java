package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "PO number is required")
    private String poNumber;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
    
    @NotNull(message = "Date issued is required")
    private LocalDate dateIssued;
    
    private String notes;
    
    @NotNull(message = "Supplier is required")
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    
    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private SpendCategory category;
    
    public PurchaseOrder() {}
    
    public PurchaseOrder(String poNumber, BigDecimal amount, LocalDate dateIssued, Supplier supplier, SpendCategory category) {
        this.poNumber = poNumber;
        this.amount = amount;
        this.dateIssued = dateIssued;
        this.supplier = supplier;
        this.category = category;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPoNumber() {
        return poNumber;
    }
    
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public LocalDate getDateIssued() {
        return dateIssued;
    }
    
    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Supplier getSupplier() {
        return supplier;
    }
    
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public SpendCategory getCategory() {
        return category;
    }
    
    public void setCategory(SpendCategory category) {
        this.category = category;
    }
}