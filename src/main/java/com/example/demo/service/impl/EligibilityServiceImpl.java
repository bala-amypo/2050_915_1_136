
package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.EligibilityService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final EligibilityResultRepository repo;
    private final LoanRequestRepository loanRepo;

    public EligibilityServiceImpl(EligibilityResultRepository repo, LoanRequestRepository loanRepo) {
        this.repo = repo;
        this.loanRepo = loanRepo;
    }

   @Override
public EligibilityResult checkEligibility(Long requestId) { // Changed name to match interface
    // 1. Return existing result if present (Fixes t53)
    Optional<EligibilityResult> existing = repo.findByLoanRequestId(requestId);
    if (existing.isPresent()) {
        return existing.get();
    }

    LoanRequest loanRequest = loanRepo.findById(requestId)
            .orElseThrow(() -> new BadRequestException("Loan request not found"));

    EligibilityResult result = new EligibilityResult();
    result.setLoanRequest(loanRequest);
    
    // Add your calculation logic here
    
    return repo.save(result);
}
}