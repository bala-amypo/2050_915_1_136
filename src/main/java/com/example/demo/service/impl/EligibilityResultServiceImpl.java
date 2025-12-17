package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.EligibilityResult;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.service.EligibilityResultService;

import java.util.List;
import java.util.Optional;

@Service
public class EligibiltyResultServiceImpl implements EligibilityResultService {

    @Autowired
    private EligibilityResultRepository repository;

    @Override
    public EligibilityResult createResult(EligibilityResult result) {
        return repository.save(result);
    }

    @Override
    public EligibilityResult updateResult(Long id, EligibilityResult result) {
        Optional<EligibilityResult> existing = repository.findById(id);
        if(existing.isPresent()) {
            EligibilityResult er = existing.get();
            er.setLoanRequest(result.getLoanRequest());
            er.setIsEligible(result.getIsEligible());
            er.setMaxEligibleAmount(result.getMaxEligibleAmount());
            er.setEstimatedEmi(result.getEstimatedEmi());
            er.setRiskLevel(result.getRiskLevel());
            er.setRejectionReason(result.getRejectionReason());
            return repository.save(er);
        }
        return null;
    }

    @Override
    public Optional<EligibilityResult> getResultById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<EligibilityResult> getAllResults() {
        return repository.findAll();
    }

    @Override
    public boolean deleteResult(Long id) {
        Optional<EligibilityResult> existing = repository.findById(id);
        if(existing.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
