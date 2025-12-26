package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loan")
public class LoanRequestController {

    private final LoanRequestService service;

    public LoanRequestController(LoanRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> submit(@RequestBody LoanDtos.LoanRequestDto dto) {
        LoanRequest saved = service.submitRequest(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan request submitted successfully");
        response.put("data", saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> byUser(@PathVariable Long userId) {
        List<LoanRequest> list = service.getRequestsByUser(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan requests fetched successfully");
        response.put("data", list);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        LoanRequest request = service.getRequestById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan request fetched successfully");
        response.put("data", request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<LoanRequest> list = service.getAllRequests();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All loan requests fetched successfully");
        response.put("data", list);

        return ResponseEntity.ok(response);
    }
}
