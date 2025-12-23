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

    // ✅ Log a new risk assessment
    @PostMapping
    public ResponseEntity<Map<String, Object>> logAssessment(
            @RequestBody RiskLog log) {

        RiskLog savedLog = service.logAssessment(log);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Risk assessment log saved successfully");
        response.put("data", savedLog);

        return ResponseEntity.ok(response);
    }

    // ✅ Get logs by loan request ID
    @GetMapping("/{loanId}")
    public ResponseEntity<Map<String, Object>> getLogsByRequest(
            @PathVariable Long loanId) {

        List<RiskLog> logs = service.getLogsByRequest(loanId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Risk assessment logs fetched successfully");
        response.put("data", logs);

        return ResponseEntity.ok(response);
    }
}
