package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;

public interface EligibilityService {
    EligibilityResult evaluateEligibility(long requestId);
    // Add other methods as defined in your implementation
}