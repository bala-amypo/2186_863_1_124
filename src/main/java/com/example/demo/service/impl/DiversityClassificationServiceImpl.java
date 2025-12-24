package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiversityClassificationServiceImpl
        implements DiversityClassificationService {

    private final DiversityClassificationRepository repository;

    public DiversityClassificationServiceImpl(
            DiversityClassificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiversityClassification createClassification(
            DiversityClassification classification) {
        return repository.save(classification);
    }

    @Override
    public DiversityClassification getClassificationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Classification not found with id: " + id));
    }

    @Override
    public List<DiversityClassification> getAllClassifications() {
        return repository.findAll();
    }

    @Override
    public DiversityClassification updateClassification(
            Long id, DiversityClassification classification) {

        DiversityClassification existing = getClassificationById(id);
        existing.setCode(classification.getCode());
        existing.setDescription(classification.getDescription());

        return repository.save(existing);
    }

    @Override
    public void deactivateClassification(Long id) {
        DiversityClassification classification = getClassificationById(id);
        classification.setActive(false);
        repository.save(classification);
    }
}
