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

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository categoryRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository poRepository,
                                    SupplierRepository supplierRepository,
                                    SpendCategoryRepository categoryRepository) {
        this.poRepository = poRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        Supplier supplier = supplierRepository.findById(po.getSupplier().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        SpendCategory category = categoryRepository.findById(po.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (!supplier.getIsActive()) throw new BadRequestException("Supplier inactive");
        if (!category.getActive()) throw new BadRequestException("Category inactive");
        if (po.getAmount().signum() <= 0) throw new BadRequestException("Invalid amount");
        if (po.getDateIssued().isAfter(LocalDate.now())) throw new BadRequestException("Future date not allowed");

        po.setSupplier(supplier);
        po.setCategory(category);

        return poRepository.save(po);
    }

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder po) {
        PurchaseOrder existing = getPurchaseOrderById(id);
        existing.setPoNumber(po.getPoNumber());
        existing.setAmount(po.getAmount());
        existing.setDateIssued(po.getDateIssued());
        existing.setNotes(po.getNotes());
        return poRepository.save(existing);
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {
        return poRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found"));
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return poRepository.findAll();
    }

    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return poRepository.findBySupplier_Id(supplierId);
    }
}
