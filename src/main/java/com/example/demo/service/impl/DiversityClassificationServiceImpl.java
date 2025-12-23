package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiversityClassificationServiceImpl implements DiversityClassificationService {
    
    private final DiversityClassificationRepository classificationRepository;
    
    public DiversityClassificationServiceImpl(DiversityClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }
    
    @Override
    public DiversityClassification createClassification(DiversityClassification classification) {
        return classificationRepository.save(classification);
    }
    
    @Override
    public DiversityClassification updateClassification(Long id, DiversityClassification classification) {
        DiversityClassification existing = getClassificationById(id);
        
        if (classification.getCode() != null) {
            existing.setCode(classification.getCode());
        }
        if (classification.getDescription() != null) {
            existing.setDescription(classification.getDescription());
        }
        if (classification.getActive() != null) {
            existing.setActive(classification.getActive());
        }
        
        return classificationRepository.save(existing);
    }
    
    @Override
    public DiversityClassification getClassificationById(Long id) {
        return classificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification not found with id: " + id));
    }
    
    @Override
    public List<DiversityClassification> getAllClassifications() {
        return classificationRepository.findAll();
    }
    
    @Override
    public void deactivateClassification(Long id) {
        DiversityClassification classification = getClassificationById(id);
        classification.setActive(false);
        classificationRepository.save(classification);
    }
}