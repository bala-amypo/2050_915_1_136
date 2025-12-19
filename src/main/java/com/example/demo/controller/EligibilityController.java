package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eligibility")
public class EligibilityResultController {

    @Autowired
    private EligibilityResultService eligibilityService;

    // CREATE
    @PostMapping
    public ResponseEntity<EligibilityResult> createResult(@RequestBody EligibilityResult result) {
        EligibilityResult createdResult = eligibilityService.createResult(result);
        return ResponseEntity.ok(createdResult);
    }

    // READ - get all results
    @GetMapping
    public ResponseEntity<List<EligibilityResult>> getAllResults() {
        List<EligibilityResult> results = eligibilityService.getAllResults();
        return ResponseEntity.ok(results);
    }

    // READ - get result by ID
    @GetMapping("/{id}")
    public ResponseEntity<EligibilityResult> getResultById(@PathVariable Long id) {
        Optional<EligibilityResult> resultOpt = eligibilityService.getResultById(id);
        return resultOpt.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<EligibilityResult> updateResult(
            @PathVariable Long id,
            @RequestBody EligibilityResult result) {
        try {
            EligibilityResult updatedResult = eligibilityService.updateResult(id, result);
            return ResponseEntity.ok(updatedResult);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        boolean deleted = eligibilityService.deleteResult(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
