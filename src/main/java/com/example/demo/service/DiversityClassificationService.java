package com.example.demo.service;

import com.example.demo.entity.DiversityClassification;

import java.util.List;

public interface DiversityClassificationService {

    DiversityClassification createClassification(DiversityClassification classification);

    DiversityClassification getClassificationById(Long id);

    List<DiversityClassification> getAllClassifications();

    DiversityClassification updateClassification(Long id, DiversityClassification classification);

    void deactivateClassification(Long id);
}
