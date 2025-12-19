package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    private final LoanRequestService service;

    public LoanRequestController(LoanRequestService service) {
        this.service = service;
    }

    // ✅ SUBMIT LOAN REQUEST
    @PostMapping
    public ResponseEntity<ModelMap> submit(@RequestBody LoanRequest lr) {

        LoanRequest saved = service.submitRequest(lr);

        ModelMap map = new ModelMap();
        map.addAttribute("message", "Loan request submitted successfully");
        map.addAttribute("data", saved);

        return ResponseEntity.ok(map);
    }

    // ✅ GET LOAN REQUESTS BY USER
    @GetMapping("/user/{id}")
    public ResponseEntity<ModelMap> byUser(@PathVariable Long id) {

        List<LoanRequest> list = service.getRequestsByUser(id);

        ModelMap map = new ModelMap();
        map.addAttribute("message", "Loan requests fetched successfully");
        map.addAttribute("data", list);

        return ResponseEntity.ok(map);
    }
}
