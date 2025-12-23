package com.example.demo.controller;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/risk/logs")
public class RiskLogController {

    private final RiskAssessmentLogService service;

    public RiskLogController(RiskAssessmentLogService service) {
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
