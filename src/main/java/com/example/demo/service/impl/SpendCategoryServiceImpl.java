package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import jakarta.persistence.EntityNotFoundException;
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
        return repository.save(category);
    }

    @Override
    public SpendCategory getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Category not found with id: " + id));
    }

    @Override
    public List<SpendCategory> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public SpendCategory updateCategory(Long id, SpendCategory category) {
        SpendCategory existing = getCategoryById(id);
        existing.setName(category.getName());
        return repository.save(existing);
    }

    @Override
    public void deactivateCategory(Long id) {
        SpendCategory category = getCategoryById(id);
        category.setActive(false);
        repository.save(category);
    }
}
