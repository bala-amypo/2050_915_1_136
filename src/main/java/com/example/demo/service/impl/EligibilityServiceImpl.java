package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final EligibilityResultRepository eligibilityRepo;

    public EligibilityServiceImpl(LoanRequestRepository loanRepo,
                                      EligibilityResultRepository eligibilityRepo) {
        this.loanRepo = loanRepo;
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityResult checkEligibility(Long loanRequestId) {
        LoanRequest lr = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(lr);

        // Simple logic (exam-friendly)
        result.setEligible(lr.getRequestedAmount() <= 100000);
        result.setMaxEligibleAmount(100000.0);

        return eligibilityRepo.save(result);
    }
}
