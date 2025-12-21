package com.example.demo.service.impl;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;
@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRequestRepository;

    public EligibilityServiceImpl(LoanRequestRepository loanRequestRepository) {
        this.loanRequestRepository = loanRequestRepository;
    }

    @Override
    public boolean checkEligibility(Long loanRequestId) {

        LoanRequest loan = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan Request not found"));

        boolean eligible = loan.getRequestedAmount() <= 500000
                && loan.getTenureMonths() <= 60;

        loan.setEligibilityResult(eligible ? "Eligible" : "Not Eligible");
        loanRequestRepository.save(loan);

        return eligible;
    }

    // ✅ ADD THIS METHOD
    @Override
    public String getEligibilityResult(Long loanRequestId) {

        LoanRequest loan = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan Request not found"));

        return loan.getEligibilityResult();
    }
}
