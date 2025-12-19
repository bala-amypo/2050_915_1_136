package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    private final LoanRequestService service;

    public LoanRequestController(LoanRequestService service) {
        this.service = service;
    }

    @PostMapping
    public LoanRequest submit(@RequestBody LoanRequest lr) {
        return service.submitRequest(lr);
    }

    @GetMapping("/user/{id}")
    public List<LoanRequest> byUser(@PathVariable Long id) {
        return service.getRequestsByUser(id);
    }
}
