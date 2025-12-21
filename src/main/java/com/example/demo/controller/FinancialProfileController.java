package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {

    private final FinancialProfileService financialProfileService;

    public FinancialProfileController(FinancialProfileService financialProfileService) {
        this.financialProfileService = financialProfileService;
    }

    // POST /
    @PostMapping
    public ResponseEntity<?> saveProfile(@RequestBody FinancialProfileDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(financialProfileService.save(dto));
    }

    // GET /user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                financialProfileService.getByUserId(userId)
        );
    }
}
