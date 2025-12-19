package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/risk")
public class RiskLogController {

    private final RiskAssessmentService service;

    public RiskLogController(RiskAssessmentService service) {
        this.service = service;
    }

    @PostMapping("/{loanId}")
    public ResponseEntity<Map<String, Object>> assess(
            @PathVariable Long loanId) {

        RiskAssessment result = service.assessRisk(loanId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Risk assessment completed");
        response.put("data", result);

        return ResponseEntity.ok(response);
    }
}
