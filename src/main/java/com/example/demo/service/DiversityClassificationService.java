package com.example.demo.service;

import com.example.demo.entity.DiversityClassification;

import java.util.List;

public interface DiversityClassificationService {
    DiversityClassification createClassification(DiversityClassification dc);
    DiversityClassification updateClassification(Long id, DiversityClassification dc);
    DiversityClassification getById(Long id);
    List<DiversityClassification> getAll();
    void deactivate(Long id);
}
