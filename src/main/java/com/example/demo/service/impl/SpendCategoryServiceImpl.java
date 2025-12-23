package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {

    private final SpendCategoryRepository repository;

    public SpendCategoryServiceImpl(SpendCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpendCategory createCategory(SpendCategory category) {
        category.setActive(true);
        return repository.save(category);
    }

    @Override
    public SpendCategory updateCategory(Long id, SpendCategory category) {
        SpendCategory existing = getById(id);
        existing.setName(category.getName());
        return repository.save(existing);
    }

    @Override
    public SpendCategory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<SpendCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        SpendCategory category = getById(id);
        category.setActive(false);
        repository.save(category);
    }
}
