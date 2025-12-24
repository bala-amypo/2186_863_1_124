package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classifications")
public class DiversityClassificationController {

    private final DiversityClassificationService service;

    public DiversityClassificationController(DiversityClassificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiversityClassification> create(
            @RequestBody DiversityClassification classification) {
        return new ResponseEntity<>(service.createClassification(classification), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassification> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClassificationById(id));
    }

    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAll() {
        return ResponseEntity.ok(service.getAllClassifications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiversityClassification> update(
            @PathVariable Long id,
            @RequestBody DiversityClassification classification) {
        return ResponseEntity.ok(service.updateClassification(id, classification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateClassification(id);
        return ResponseEntity.noContent().build();
    }
}
