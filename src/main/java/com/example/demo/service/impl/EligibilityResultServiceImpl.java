package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.service.EligibilityResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EligibilityResultServiceImpl implements EligibilityResultService {

    @Autowired
    private EligibilityResultRepository repository;

    @Override
    public EligibilityResult createResult(EligibilityResult result) {
        return repository.save(result);
    }

    @Override
    public EligibilityResult updateResult(Long id, EligibilityResult result) {
        return repository.findById(id)
                .map(existingResult -> {
                    // Update fields based on your entity
                    existingResult.setDtiRatio(result.getDtiRatio());
                    existingResult.setCreditCheckStatus(result.getCreditCheckStatus());
                    existingResult.setTimestamp(result.getTimestamp());
                    return repository.save(existingResult);
                })
                .orElseThrow(() -> new RuntimeException("EligibilityResult not found with id " + id));
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
        return repository.findById(id)
                .map(result -> {
                    repository.delete(result);
                    return true;
                })
                .orElse(false);
    }
}
