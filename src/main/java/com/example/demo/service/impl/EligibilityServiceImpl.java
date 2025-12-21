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
public String checkEligibility(Long loanRequestId) {

    LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
            .orElseThrow(() -> new RuntimeException("Loan not found"));

    FinancialProfile profile = financialProfileRepository
            .findByUserId(loanRequest.getUser().getId())
            .orElseThrow(() -> new RuntimeException("Financial profile not found"));

    String result = profile.getCreditScore() >= 650 ? "Eligible" : "Not Eligible";

    // 🔥 THIS LINE IS IMPORTANT
    loanRequest.setEligibilityResult(result);
    loanRequestRepository.save(loanRequest);

    return result;
}

    // ✅ ADD THIS METHOD
    @Override
    public String getEligibilityResult(Long loanRequestId) {

        LoanRequest loan = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan Request not found"));

        return loan.getEligibilityResult();
    }
}
