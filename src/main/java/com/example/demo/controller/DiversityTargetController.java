package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {
    
    private final DiversityTargetService targetService;
    
    public DiversityTargetController(DiversityTargetService targetService) {
        this.targetService = targetService;
    }
    
    @PostMapping
    public ResponseEntity<DiversityTarget> createTarget(@Valid @RequestBody DiversityTarget target) {
        return ResponseEntity.ok(targetService.createTarget(target));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DiversityTarget> updateTarget(@PathVariable Long id, @RequestBody DiversityTarget target) {
        return ResponseEntity.ok(targetService.updateTarget(id, target));
    }
    
    @GetMapping
    public ResponseEntity<List<DiversityTarget>> getAllTargets() {
        return ResponseEntity.ok(targetService.getAllTargets());
    }
    
    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiversityTarget>> getTargetsByYear(@PathVariable int year) {
        return ResponseEntity.ok(targetService.getTargetsByYear(year));
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateTarget(@PathVariable Long id) {
        targetService.deactivateTarget(id);
        return ResponseEntity.ok().build();
    }
}