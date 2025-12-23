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

    // ✅ SUBMIT LOAN REQUEST
    @PostMapping
    public ResponseEntity<Map<String, Object>> submit(
            @RequestBody LoanRequest lr) {

        LoanRequest saved = service.submitRequest(lr);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan request submitted successfully");
        response.put("data", saved);

        return ResponseEntity.ok(response);
    }

    // ✅ GET LOAN REQUESTS BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> byUser(
            @PathVariable Long userId) {

        List<LoanRequest> list = service.getRequestsByUser(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan requests fetched successfully");
        response.put("data", list);

        return ResponseEntity.ok(response);
    }

    // ✅ GET LOAN REQUEST BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(
            @PathVariable Long id) {

        LoanRequest request = service.getRequestById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan request fetched successfully");
        response.put("data", request);

        return ResponseEntity.ok(response);
    }

    // ✅ GET ALL LOAN REQUESTS
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {

        List<LoanRequest> list = service.getAllRequests();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All loan requests fetched successfully");
        response.put("data", list);

        return ResponseEntity.ok(response);
    }
}
