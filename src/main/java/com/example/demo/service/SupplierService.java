package com.example.demo.service;

import com.example.demo.entity.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier createSupplier(Supplier supplier);

    Supplier getSupplierById(Long id);

    List<Supplier> getAllSuppliers();

    Supplier updateSupplier(Long id, Supplier supplier);

    void deactivateSupplier(Long id);
}
