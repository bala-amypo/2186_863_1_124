package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.entity.SpendCategory;
import com.example.demo.entity.Supplier;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository categoryRepository;
    
    public PurchaseOrderServiceImpl(
            PurchaseOrderRepository purchaseOrderRepository,
            SupplierRepository supplierRepository,
            SpendCategoryRepository categoryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        validatePurchaseOrder(po);
        
        // Load supplier
        Supplier supplier = supplierRepository.findById(po.getSupplier().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        
        // Load category
        SpendCategory category = categoryRepository.findById(po.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        // Validate supplier is active
        if (!supplier.getIsActive()) {
            throw new BadRequestException("Supplier is not active");
        }
        
        // Validate category is active
        if (!category.getActive()) {
            throw new BadRequestException("Category is not active");
        }
        
        po.setSupplier(supplier);
        po.setCategory(category);
        
        return purchaseOrderRepository.save(po);
    }
    
    @Override
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder po) {
        PurchaseOrder existing = getPurchaseOrderById(id);
        
        if (po.getPoNumber() != null) {
            existing.setPoNumber(po.getPoNumber());
        }
        if (po.getAmount() != null) {
            if (po.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BadRequestException("Amount must be positive");
            }
            existing.setAmount(po.getAmount());
        }
        if (po.getDateIssued() != null) {
            if (po.getDateIssued().isAfter(LocalDate.now())) {
                throw new BadRequestException("Date issued cannot be in the future");
            }
            existing.setDateIssued(po.getDateIssued());
        }
        if (po.getNotes() != null) {
            existing.setNotes(po.getNotes());
        }
        if (po.getSupplier() != null && po.getSupplier().getId() != null) {
            Supplier supplier = supplierRepository.findById(po.getSupplier().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
            if (!supplier.getIsActive()) {
                throw new BadRequestException("Supplier is not active");
            }
            existing.setSupplier(supplier);
        }
        if (po.getCategory() != null && po.getCategory().getId() != null) {
            SpendCategory category = categoryRepository.findById(po.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            if (!category.getActive()) {
                throw new BadRequestException("Category is not active");
            }
            existing.setCategory(category);
        }
        
        return purchaseOrderRepository.save(existing);
    }
    
    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found with id: " + id));
    }
    
    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }
    
    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return purchaseOrderRepository.findBySupplier_Id(supplierId);
    }
    
    private void validatePurchaseOrder(PurchaseOrder po) {
        // Validate amount is positive
        if (po.getAmount() == null || po.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        
        // Validate date is not in future
        if (po.getDateIssued() != null && po.getDateIssued().isAfter(LocalDate.now())) {
            throw new BadRequestException("Date issued cannot be in the future");
        }
    }
}