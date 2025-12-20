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
import java.util.Optional;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository repo;

    // Matches the 3-argument constructor the test suite expects
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
        // FIX for t53: The test specifically checks for a duplicate evaluation.
        // If a result already exists for this loan request, we MUST throw an exception.
        if (repo.findByLoanRequestId(requestId).isPresent()) {
            throw new BadRequestException("Eligibility already exists");
        }

        // Fetch the loan request
        LoanRequest loanRequest = loanRepo.findById(requestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        // Fetch the user's financial profile
        FinancialProfile profile = profileRepo.findByUserId(loanRequest.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));

        // Calculation Logic (Satisfies the business logic requirements)
        double monthlyIncome = profile.getMonthlyIncome();
        double monthlyExpenses = profile.getMonthlyExpenses();
        double existingEmis = profile.getExistingEmis();
        double requestedAmount = loanRequest.getRequestedAmount();
        int tenure = loanRequest.getTenureMonths();

        double disposableIncome = monthlyIncome - monthlyExpenses - existingEmis;
        double monthlyInstallment = requestedAmount / tenure;

        boolean isEligible = disposableIncome >= (monthlyInstallment * 1.5) && profile.getCreditScore() >= 700;

        // Create and save result
        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setEligible(isEligible);
        result.setDisposableIncome(disposableIncome);
        result.setMaxEmiPossible(disposableIncome / 1.5);

        return repo.save(result);
    }

    // Method to support the "getByLoanRequestId" compiler error from earlier
    public EligibilityResult getByLoanRequestId(long requestId) {
        return repo.findByLoanRequestId(requestId)
                .orElseThrow(() -> new BadRequestException("Eligibility result not found"));
    }
}