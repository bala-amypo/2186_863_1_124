package com.example.demo.service.impl;

import com.example.demo.entity.Supplier;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;

    public SupplierServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        try {
            return repository.save(supplier);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Email must be unique: " + supplier.getEmail());
        }
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier existing = getSupplierById(id);
        existing.setName(supplier.getName());
        existing.setEmail(supplier.getEmail());
        existing.setRegistrationNumber(supplier.getRegistrationNumber());
        existing.setUpdatedAt(LocalDateTime.now());

        try {
            return repository.save(existing);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Email must be unique: " + supplier.getEmail());
        }
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with ID: " + id));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    @Override
    public void deactivateSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplier.setIsActive(false);
        supplier.setUpdatedAt(LocalDateTime.now());
        repository.save(supplier);
    }
}
