package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository repo;

    public EligibilityServiceImpl(
            LoanRequestRepository lr,
            FinancialProfileRepository fpr,
            EligibilityResultRepository r) {
        this.loanRepo = lr;
        this.profileRepo = fpr;
        this.repo = r;
    }

    @Override
    public EligibilityResult checkEligibility(long requestId) {
        if (repo.findByLoanRequestId(requestId).isPresent())
            throw new BadRequestException("Eligibility already exists");

        LoanRequest lr = loanRepo.findById(requestId)
                .orElseThrow(() -> new BadRequestException("Loan request not found"));

        if (lr.getUser() == null || lr.getUser().getId() == null) {
            throw new BadRequestException("User not associated with loan request");
        }

        FinancialProfile fp = profileRepo.findTopByUserIdOrderByCreatedAtDesc(lr.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Financial profile not found"));

        double disposable = fp.getMonthlyIncome() - fp.getMonthlyExpenses() - fp.getExistingEmis();
        double emi = lr.getRequestedAmount() / lr.getTenureMonths();
        boolean eligible = disposable >= (emi * 1.5) && fp.getCreditScore() >= 700;

        EligibilityResult res = new EligibilityResult();
        res.setLoanRequest(lr);
        res.setEligible(eligible);
        res.setDisposableIncome(disposable);
        res.setMaxEmiPossible(disposable / 1.5);

        return repo.save(res);
    }

   @Override
public EligibilityResult getByLoanRequestId(long requestId) {
    return repo.findByLoanRequestId(requestId)
            .orElseThrow(() -> new BadRequestException("Result not found"));
}

}
