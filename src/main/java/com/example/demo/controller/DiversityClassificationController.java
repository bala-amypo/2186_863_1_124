package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classifications")
public class DiversityClassificationController {
    
    private final DiversityClassificationService classificationService;
    
    public DiversityClassificationController(DiversityClassificationService classificationService) {
        this.classificationService = classificationService;
    }
    
    @PostMapping
    public ResponseEntity<DiversityClassification> createClassification(@Valid @RequestBody DiversityClassification classification) {
        return ResponseEntity.ok(classificationService.createClassification(classification));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DiversityClassification> updateClassification(@PathVariable Long id, @RequestBody DiversityClassification classification) {
        return ResponseEntity.ok(classificationService.updateClassification(id, classification));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassification> getClassificationById(@PathVariable Long id) {
        return ResponseEntity.ok(classificationService.getClassificationById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAllClassifications() {
        return ResponseEntity.ok(classificationService.getAllClassifications());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateClassification(@PathVariable Long id) {
        classificationService.deactivateClassification(id);
        return ResponseEntity.ok().build();
    }
}
