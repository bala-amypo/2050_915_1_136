package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.service.EligibilityService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository repo;

    // Matches the exact constructor signature found in the error logs
    public EligibilityServiceImpl(LoanRequestRepository loanRepo, 
                                  FinancialProfileRepository profileRepo, 
                                  EligibilityResultRepository repo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.repo = repo;
    }

    // Fixes the "evaluateEligibility" symbol error
    @Override
    public EligibilityResult evaluateEligibility(long requestId) {
        // t53: Return existing if present
        Optional<EligibilityResult> existing = repo.findByLoanRequestId(requestId);
        if (existing.isPresent()) {
            return existing.get();
        }

        LoanRequest loanRequest = loanRepo.findById(requestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        
        // Ensure you add your calculation logic here if needed
        
        return repo.save(result);
    }

    // Fixes the "getByLoanRequestId" symbol error
    public EligibilityResult getByLoanRequestId(long requestId) {
        return repo.findByLoanRequestId(requestId)
                .orElseThrow(() -> new BadRequestException("Result not found"));
    }

    // Keep this if your Interface requires it, otherwise remove it
  
    public EligibilityResult checkEligibility(Long requestId) {
        return evaluateEligibility(requestId);
    }
}