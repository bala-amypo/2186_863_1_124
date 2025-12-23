package com.example.demo.service.impl;

import com.example.demo.entity.Supplier;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    
    private final SupplierRepository supplierRepository;
    
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    
    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier existing = getSupplierById(id);
        
        if (supplier.getName() != null) {
            existing.setName(supplier.getName());
        }
        if (supplier.getEmail() != null) {
            existing.setEmail(supplier.getEmail());
        }
        if (supplier.getRegistrationNumber() != null) {
            existing.setRegistrationNumber(supplier.getRegistrationNumber());
        }
        if (supplier.getIsActive() != null) {
            existing.setIsActive(supplier.getIsActive());
        }
        if (supplier.getDiversityClassifications() != null) {
            existing.setDiversityClassifications(supplier.getDiversityClassifications());
        }
        
        existing.setUpdatedAt(LocalDateTime.now());
        return supplierRepository.save(existing);
    }
    
    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }
    
    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    
    @Override
    public void deactivateSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplier.setIsActive(false);
        supplier.setUpdatedAt(LocalDateTime.now());
        supplierRepository.save(supplier);
    }
}