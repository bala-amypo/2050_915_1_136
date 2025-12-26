package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/risk/logs")
public class RiskLogController {

    private final RiskAssessmentService service;

    public RiskLogController(RiskAssessmentService service) {
        this.service = service;
    }

    // CREATE RISK LOG
    @PostMapping
    public ResponseEntity<Map<String, Object>> logAssessment(
            @RequestBody RiskAssessmentLog log) {

        RiskAssessmentLog saved = service.logAssessment(log);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Risk assessment logged successfully");
        response.put("data", saved);

        return ResponseEntity.ok(response);
    }

    // GET LOGS BY LOAN REQUEST ID
    @GetMapping("/{loanRequestId}")
    public ResponseEntity<Map<String, Object>> getLogs(
            @PathVariable Long loanRequestId) {

        List<RiskAssessmentLog> logs =
                service.getLogsByRequest(loanRequestId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Risk logs fetched successfully");
        response.put("data", logs);

        return ResponseEntity.ok(response);
    }
}
