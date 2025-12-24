package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(service.createSupplier(supplier), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(service.getAllSuppliers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable Long id,
            @RequestBody Supplier supplier) {
        return ResponseEntity.ok(service.updateSupplier(id, supplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateSupplier(@PathVariable Long id) {
        service.deactivateSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
