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
    public ResponseEntity<DiversityClassification> create(@RequestBody DiversityClassification dc) {
        return new ResponseEntity<>(service.createClassification(dc), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiversityClassification> update(@PathVariable Long id, @RequestBody DiversityClassification dc) {
        return ResponseEntity.ok(service.updateClassification(id, dc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassification> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
