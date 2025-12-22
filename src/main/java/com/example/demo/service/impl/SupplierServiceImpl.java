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

    private final SupplierRepository repository;

    public SupplierServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    public Supplier createSupplier(Supplier supplier) {
        return repository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier existing = getSupplierById(id);
        existing.setName(supplier.getName());
        existing.setEmail(supplier.getEmail());
        existing.setRegistrationNumber(supplier.getRegistrationNumber());
        existing.setUpdatedAt(LocalDateTime.now());
        return repository.save(existing);
    }

    public Supplier getSupplierById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    public void deactivateSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplier.setIsActive(false);
        supplier.setUpdatedAt(LocalDateTime.now());
        repository.save(supplier);
    }
}
