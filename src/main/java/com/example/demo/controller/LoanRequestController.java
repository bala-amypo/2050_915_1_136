package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    private final LoanRequestService service;

    public LoanRequestController(LoanRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> submit(
            @RequestBody LoanRequest lr) {

        LoanRequest saved = service.submitRequest(lr);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan request submitted successfully");
        response.put("data", saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> byUser(
            @PathVariable Long id) {

        List<LoanRequest> list = service.getRequestsByUser(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan requests fetched successfully");
        response.put("data", list);

        return ResponseEntity.ok(response);
    }
}
