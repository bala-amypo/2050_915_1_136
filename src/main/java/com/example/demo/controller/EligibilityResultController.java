package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityResultService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eligibility-results")
public class EligibilityResultController {

    @Autowired
    private EligibilityResultService service;

    // CREATE
    @PostMapping
    public EligibilityResult createResult(@RequestBody EligibilityResult result) {
        return service.createResult(result);
    }

    // GET ALL
    @GetMapping
    public List<EligibilityResult> getAllResults() {
        return service.getAllResults();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<EligibilityResult> getResultById(@PathVariable Long id) {
        return service.getResultById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public EligibilityResult updateResult(@PathVariable Long id, @RequestBody EligibilityResult result) {
        return service.updateResult(id, result);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteResult(@PathVariable Long id) {
        boolean deleted = service.deleteResult(id);
        return deleted ? "Deleted successfully" : "Result not found";
    }
}
