package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.service.EligibilityService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository repo;

    public EligibilityServiceImpl(LoanRequestRepository loanRepo, 
                                  FinancialProfileRepository profileRepo, 
                                  EligibilityResultRepository repo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.repo = repo;
    }

    @Override
    @Transactional
    public EligibilityResult evaluateEligibility(long requestId) {
        // Fix for t53: Duplicate check
        if (repo.findByLoanRequestId(requestId).isPresent()) {
            throw new BadRequestException("Eligibility already exists");
        }

        LoanRequest loanRequest = loanRepo.findById(requestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        FinancialProfile profile = profileRepo.findByUserId(loanRequest.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));

        // Calculation Logic
        double monthlyIncome = profile.getMonthlyIncome();
        double monthlyExpenses = profile.getMonthlyExpenses();
        double existingEmis = profile.getExistingEmis();
        double requestedAmount = loanRequest.getRequestedAmount();
        int tenure = loanRequest.getTenureMonths();

        double disposableIncome = monthlyIncome - monthlyExpenses - existingEmis;
        double monthlyInstallment = requestedAmount / tenure;

        // Eligibility Criteria: Disposable income > 1.5x EMI AND Credit Score >= 700
        boolean isEligible = disposableIncome >= (monthlyInstallment * 1.5) && profile.getCreditScore() >= 700;

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setEligible(isEligible);
        result.setDisposableIncome(disposableIncome);
        
        // IMPORTANT: Use the method name that exists in your EligibilityResult entity
        // If your entity uses setMaxEligibleAmount, change this line to:
        // result.setMaxEligibleAmount(disposableIncome / 1.5);
        result.setMaxEmiPossible(disposableIncome / 1.5);

        return repo.save(result);
    }

    public EligibilityResult getByLoanRequestId(long requestId) {
        return repo.findByLoanRequestId(requestId)
                .orElseThrow(() -> new BadRequestException("Eligibility result not found"));
    }
}