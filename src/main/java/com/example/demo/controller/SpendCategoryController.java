package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendCategoryController {
    
    private final SpendCategoryService categoryService;
    
    public SpendCategoryController(SpendCategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @PostMapping
    public ResponseEntity<SpendCategory> createCategory(@Valid @RequestBody SpendCategory category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SpendCategory> updateCategory(@PathVariable Long id, @RequestBody SpendCategory category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SpendCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCategory(@PathVariable Long id) {
        categoryService.deactivateCategory(id);
        return ResponseEntity.ok().build();
    }
}
