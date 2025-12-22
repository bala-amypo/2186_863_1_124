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
            throw new BadRequestException("Target percentage must be between 0 and 100");
        return repository.save(target);
    }

    public DiversityTarget updateTarget(Long id, DiversityTarget target) {
        DiversityTarget existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diversity target not found"));

        if (target.getTargetPercentage() < 0 || target.getTargetPercentage() > 100)
            throw new BadRequestException("Target percentage must be between 0 and 100");

        existing.setTargetYear(target.getTargetYear());
        existing.setTargetPercentage(target.getTargetPercentage());
        existing.setClassification(target.getClassification());

        return repository.save(existing);
    }

    public List<DiversityTarget> getAllTargets() {
        return repository.findAll();
    }

    public List<DiversityTarget> getTargetsByYear(int year) {
        return repository.findByTargetYear(year);
    }

    public void deactivateTarget(Long id) {
        DiversityTarget target = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diversity target not found"));
        target.setActive(false);
        repository.save(target);
    }
}
