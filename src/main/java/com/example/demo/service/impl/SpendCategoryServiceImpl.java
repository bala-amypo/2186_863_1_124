package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {
    
    private final SpendCategoryRepository categoryRepository;
    
    public SpendCategoryServiceImpl(SpendCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public SpendCategory createCategory(SpendCategory category) {
        return categoryRepository.save(category);
    }
    
    @Override
    public SpendCategory updateCategory(Long id, SpendCategory category) {
        SpendCategory existing = getCategoryById(id);
        
        if (category.getName() != null) {
            existing.setName(category.getName());
        }
        if (category.getActive() != null) {
            existing.setActive(category.getActive());
        }
        
        return categoryRepository.save(existing);
    }
    
    @Override
    public SpendCategory getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }
    
    @Override
    public List<SpendCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    @Override
    public void deactivateCategory(Long id) {
        SpendCategory category = getCategoryById(id);
        category.setActive(false);
        categoryRepository.save(category);
    }
}