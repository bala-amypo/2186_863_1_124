package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
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