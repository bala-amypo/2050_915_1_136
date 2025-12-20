package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final EligibilityResultRepository resultRepository;

    public EligibilityServiceImpl(EligibilityResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public EligibilityResult checkEligibility(LoanRequest loanRequest) {
        // Check if an eligibility result already exists
        Optional<EligibilityResult> existingResult = resultRepository.findByLoanRequest_Id(loanRequest.getId());
        if (existingResult.isPresent()) {
            return existingResult.get();
        }

        // Example eligibility logic
        boolean eligible = loanRequest.getAmount() <= 100000; // simple check
        String reason = eligible ? "Approved" : "Amount too high";

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setEligible(eligible);
        result.setReason(reason);

        return resultRepository.save(result);
    }
}
