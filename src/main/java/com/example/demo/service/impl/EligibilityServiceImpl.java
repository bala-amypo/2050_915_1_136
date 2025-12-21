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
    private final EligibilityResultRepository eligibilityResultRepository; // Added this

    // Update constructor to include the new repository
    public EligibilityServiceImpl(LoanRequestRepository loanRequestRepository, 
                                  EligibilityResultRepository eligibilityResultRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
    }

    @Override
    @Transactional // Ensures the data is committed to the database
    public boolean checkEligibility(Long loanRequestId) {

        // 1. Fetch the loan request
        LoanRequest loan = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan Request not found"));

        // 2. Perform calculations
        boolean eligible = loan.getRequestedAmount() <= 500000
                && loan.getTenureMonths() <= 60;

        // 3. Update the LoanRequest table record
        loan.setEligibilityResult(eligible ? "Eligible" : "Not Eligible");
        loanRequestRepository.save(loan);

        // 4. CREATE and SAVE the record for the 'eligibility_result' table
        // This is what makes the "Empty Set" go away!
        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loan);
        result.setEligible(eligible);
        result.setReason(eligible ? "Meets amount and tenure criteria" : "Exceeds limits");

        eligibilityResultRepository.save(result); 

        return eligible;
    }

    @Override
    public String getEligibilityResult(Long loanRequestId) {
        // Checking the specific result table first
        return eligibilityResultRepository.findByLoanRequest_Id(loanRequestId)
                .map(res -> res.isEligible() ? "Eligible" : "Not Eligible")
                .orElse("No result found");
    }
}