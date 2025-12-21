package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
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

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                    SupplierRepository supplierRepository,
                                    SpendCategoryRepository categoryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {

        Supplier supplier = supplierRepository.findById(po.getSupplier().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        SpendCategory category = categoryRepository.findById(po.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (!supplier.getIsActive())
            throw new BadRequestException("Supplier is inactive");

        if (!category.getActive())
            throw new BadRequestException("Category is inactive");

        if (po.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new BadRequestException("Amount must be positive");

        if (po.getDateIssued().isAfter(LocalDate.now()))
            throw new BadRequestException("Date issued cannot be in the future");

        po.setSupplier(supplier);
        po.setCategory(category);

        return purchaseOrderRepository.save(po);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return purchaseOrderRepository.findBySupplier_Id(supplierId);
    }
}
