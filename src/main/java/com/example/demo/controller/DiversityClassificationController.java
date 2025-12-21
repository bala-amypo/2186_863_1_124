package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
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
    public List<DiversityClassification> getAllClassifications() {
        return classificationService.getAllClassifications();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateClassification(@PathVariable Long id) {
        classificationService.deactivateClassification(id);
    }
}
