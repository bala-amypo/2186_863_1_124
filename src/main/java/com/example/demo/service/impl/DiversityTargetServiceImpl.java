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

    private final DiversityTargetRepository repository;

    public DiversityTargetServiceImpl(DiversityTargetRepository repository) {
        this.repository = repository;
    }

    public DiversityTarget createTarget(DiversityTarget target) {
        if (target.getTargetPercentage() < 0 || target.getTargetPercentage() > 100)
            throw new BadRequestException("Invalid percentage");
        return repository.save(target);
    }

    public DiversityTarget updateTarget(Long id, DiversityTarget target) {
        DiversityTarget existing = getTargetById(id);
        existing.setTargetYear(target.getTargetYear());
        existing.setTargetPercentage(target.getTargetPercentage());
        existing.setClassification(target.getClassification());
        return repository.save(existing);
    }

    public DiversityTarget getTargetById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
    }

    public List<DiversityTarget> getAllTargets() {
        return repository.findAll();
    }

    public List<DiversityTarget> getTargetsByYear(int year) {
        return repository.findByTargetYear(year);
    }

    public void deactivateTarget(Long id) {
        DiversityTarget target = getTargetById(id);
        target.setActive(false);
        repository.save(target);
    }
}
