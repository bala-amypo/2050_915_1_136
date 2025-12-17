package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;
import java.util.List;
import java.util.Optional;

public interface EligibilityService {

    // Create a new eligibility result
    EligibilityResult createResult(EligibilityResult result);

    // Update an existing eligibility result by ID
    EligibilityResult updateResult(Long id, EligibilityResult result);

    // Get an eligibility result by ID
    Optional<EligibilityResult> getResultById(Long id);

    // Get all eligibility results
    List<EligibilityResult> getAllResults();

    // Delete an eligibility result by ID
    boolean deleteResult(Long id);
}
