package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiversityTargetServiceImpl implements DiversityTargetService {
    
    private final DiversityTargetRepository targetRepository;
    
    public DiversityTargetServiceImpl(DiversityTargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }
    
    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        validateTargetPercentage(target.getTargetPercentage());
        return targetRepository.save(target);
    }
    
    @Override
    public DiversityTarget updateTarget(Long id, DiversityTarget target) {
        DiversityTarget existing = targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id: " + id));
        
        if (target.getTargetYear() != 0) {
            existing.setTargetYear(target.getTargetYear());
        }
        if (target.getTargetPercentage() != null) {
            validateTargetPercentage(target.getTargetPercentage());
            existing.setTargetPercentage(target.getTargetPercentage());
        }
        if (target.getClassification() != null) {
            existing.setClassification(target.getClassification());
        }
        if (target.getActive() != null) {
            existing.setActive(target.getActive());
        }
        
        return targetRepository.save(existing);
    }
    
    @Override
    public List<DiversityTarget> getAllTargets() {
        return targetRepository.findAll();
    }
    
    @Override
    public List<DiversityTarget> getTargetsByYear(int year) {
        return targetRepository.findByTargetYear(year);
    }
    
    @Override
    public void deactivateTarget(Long id) {
        DiversityTarget target = targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id: " + id));
        target.setActive(false);
        targetRepository.save(target);
    }
    
    private void validateTargetPercentage(Double percentage) {
        if (percentage == null || percentage < 0 || percentage > 100) {
            throw new BadRequestException("Target percentage must be between 0 and 100");
        }
    }
}