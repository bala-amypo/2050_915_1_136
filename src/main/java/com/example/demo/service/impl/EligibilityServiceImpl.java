package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRequestRepository;
    private final EligibilityResultRepository eligibilityResultRepository;

    public EligibilityServiceImpl(LoanRequestRepository loanRequestRepository, 
                                  EligibilityResultRepository eligibilityResultRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
    }

    @Override
    @Transactional
    public boolean checkEligibility(Long loanRequestId) {
        LoanRequest loan = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan Request not found"));

        boolean eligible = loan.getRequestedAmount() <= 500000 && loan.getTenureMonths() <= 60;

        // Save to eligibility_result table
        EligibilityResult res = new EligibilityResult();
        res.setLoanRequest(loan);
        res.setEligible(eligible);
        res.setReason(eligible ? "Meets criteria" : "Exceeds limits");
        eligibilityResultRepository.save(res);

        // Update LoanRequest status
        loan.setEligibilityResult(eligible ? "Eligible" : "Not Eligible");
        loanRequestRepository.save(loan);

        return eligible;
    }

    @Override
    public String getEligibilityResult(Long loanRequestId) {
        LoanRequest loan = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan Request not found"));
        return loan.getEligibilityResult();
    }
}