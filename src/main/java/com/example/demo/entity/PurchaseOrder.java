package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poNumber;

    private BigDecimal amount;

    private LocalDate dateIssued;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private SpendCategory category;

    public PurchaseOrder() {}

    public PurchaseOrder(String poNumber, BigDecimal amount, LocalDate dateIssued) {
        this.poNumber = poNumber;
        this.amount = amount;
        this.dateIssued = dateIssued;
    }

    public Long getId() { return id; }
    public String getPoNumber() { return poNumber; }
    public BigDecimal getAmount() { return amount; }
    public LocalDate getDateIssued() { return dateIssued; }
    public String getNotes() { return notes; }
    public Supplier getSupplier() { return supplier; }
    public SpendCategory getCategory() { return category; }

    public void setId(Long id) { this.id = id; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setDateIssued(LocalDate dateIssued) { this.dateIssued = dateIssued; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
    public void setCategory(SpendCategory category) { this.category = category; }
}
