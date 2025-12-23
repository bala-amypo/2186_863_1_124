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

    @Override
    public DiversityClassification createClassification(DiversityClassification classification) {
        classification.setActive(true);
        return repository.save(classification);
    }

    @Override
    public DiversityClassification updateClassification(Long id, DiversityClassification classification) {
        DiversityClassification existing = getById(id);
        existing.setName(classification.getName());
        existing.setDescription(classification.getDescription());
        return repository.save(existing);
    }

    @Override
    public DiversityClassification getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification not found"));
    }

    @Override
    public List<DiversityClassification> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        DiversityClassification dc = getById(id);
        dc.setActive(false);
        repository.save(dc);
    }
}
