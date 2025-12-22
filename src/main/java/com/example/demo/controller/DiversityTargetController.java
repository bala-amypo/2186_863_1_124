package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/targets")
public class DiversityTargetController {

    private final DiversityTargetService service;

    public DiversityTargetController(DiversityTargetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiversityTarget> create(@RequestBody DiversityTarget target) {
        return new ResponseEntity<>(service.createTarget(target), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiversityTarget> update(@PathVariable Long id,
                                                  @RequestBody DiversityTarget target) {
        return ResponseEntity.ok(service.updateTarget(id, target));
    }

    @GetMapping
    public ResponseEntity<List<DiversityTarget>> getAll() {
        return ResponseEntity.ok(service.getAllTargets());
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiversityTarget>> getByYear(@PathVariable int year) {
        return ResponseEntity.ok(service.getTargetsByYear(year));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateTarget(id);
        return ResponseEntity.noContent().build();
    }
}
