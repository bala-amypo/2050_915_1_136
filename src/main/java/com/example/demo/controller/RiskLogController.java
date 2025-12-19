package com.example.demo.controller;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/risk-logs")
public class RiskAssessmentLogController {

    @Autowired
    private RiskAssessmentLogService riskAssessmentLogService;

    // CREATE
    @PostMapping
    public ResponseEntity<RiskAssessmentLog> createLog(@RequestBody RiskAssessmentLog log) {
        RiskAssessmentLog created = riskAssessmentLogService.createLog(log);
        return ResponseEntity.ok(created);
    }

    // READ - all
    @GetMapping
    public ResponseEntity<List<RiskAssessmentLog>> getAllLogs() {
        List<RiskAssessmentLog> logs = riskAssessmentLogService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    // READ - by ID
    @GetMapping("/{id}")
    public ResponseEntity<RiskAssessmentLog> getLogById(@PathVariable Long id) {
        Optional<RiskAssessmentLog> log = riskAssessmentLogService.getLogById(id);
        return log.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<RiskAssessmentLog> updateLog(
            @PathVariable Long id,
            @RequestBody RiskAssessmentLog log) {
        try {
            RiskAssessmentLog updated = riskAssessmentLogService.updateLog(id, log);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        boolean deleted = riskAssessmentLogService.deleteLog(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
