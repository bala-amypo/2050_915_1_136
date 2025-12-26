package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/risk/logs")
public class RiskLogController {

    private final RiskAssessmentService service;

    public RiskLogController(RiskAssessmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> logAssessment(@RequestBody RiskAssessment log) {
        RiskAssessment saved = service.logAssessment(log);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Risk assessment logged successfully");
        response.put("data", saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getLogs(@PathVariable Long userId) {
        List<RiskAssessment> logs = service.getLogsByUser(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Risk logs fetched successfully");
        response.put("data", logs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/latest/{userId}")
    public ResponseEntity<Map<String, Object>> getLatest(@PathVariable Long userId) {
        RiskAssessment latest = service.getLatestByUser(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Latest risk assessment fetched successfully");
        response.put("data", latest);
        return ResponseEntity.ok(response);
    }
}
