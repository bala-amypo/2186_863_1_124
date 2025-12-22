package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiversityClassificationServiceImpl implements DiversityClassificationService {

    private final DiversityClassificationRepository repository;

    public DiversityClassificationServiceImpl(DiversityClassificationRepository repository) {
        this.repository = repository;
    }

    public DiversityClassification createClassification(DiversityClassification dc) {
        return repository.save(dc);
    }

    public DiversityClassification updateClassification(Long id, DiversityClassification dc) {
        DiversityClassification existing = getById(id);
        existing.setCode(dc.getCode());
        existing.setDescription(dc.getDescription());
        return repository.save(existing);
    }

    public DiversityClassification getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification not found"));
    }

    public List<DiversityClassification> getAll() {
        return repository.findAll();
    }

    public void deactivate(Long id) {
        DiversityClassification dc = getById(id);
        dc.setActive(false);
        repository.save(dc);
    }
}
