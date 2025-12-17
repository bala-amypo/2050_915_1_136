package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.repository.EligibilityResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    @Autowired
    private EligibilityResultRepository repository;

    public EligibilityResult createResult(EligibilityResult result) {
        return repository.save(result);
    }

   
    public EligibilityResult updateResult(Long id, EligibilityResult result) {
        return repository.findById(id)
                .map(existingResult -> {
                    existingResult.setMaxEligibleAmount(result.getMaxEligibleAmount());
                    existingResult.setInterestRate(result.getInterestRate());
                    existingResult.setTenureMonths(result.getTenureMonths());
                    existingResult.setUpdatedAt(result.getCalculatedAt());
                    return repository.save(existingResult);
                })
                .orElse(null); // return null if not found
    }

 
    public Optional<EligibilityResult> getResultById(Long id) {
        return repository.findById(id);
    }

  
    public List<EligibilityResult> getAllResults() {
        return repository.findAll();
    }

    
    public boolean deleteResult(Long id) {
        return repository.findById(id)
                .map(result -> {
                    repository.delete(result);
                    return true;
                })
                .orElse(false);
    }
}
