package com.example.demo.controller;

import com.example.demo.dto.FinancialProfileDto;
import com.example.demo.service.FinancialProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {

    private final FinancialProfileService financialProfileService;

    public FinancialProfileController(FinancialProfileService financialProfileService) {
        this.financialProfileService = financialProfileService;
    }

    // POST /api/financial-profiles
    @PostMapping
    public ResponseEntity<?> saveProfile(@RequestBody FinancialProfileDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(financialProfileService.save(dto));
    }

    // GET /api/financial-profiles/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                financialProfileService.getByUserId(userId)
        );
    }
}
