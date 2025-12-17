package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.service.FinancialProfileService;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    @Autowired
    private FinancialProfileRepository repository;

    public FinancialProfile createProfile(FinancialProfile profile) {
        return repository.save(profile);
    }

    public FinancialProfile updateProfile(Long id, FinancialProfile profile) {
        Optional<FinancialProfile> existingProfile = repository.findById(id);
        if(existingProfile.isPresent()) {
            FinancialProfile fp = existingProfile.get();
            fp.setMonthlyIncome(profile.getMonthlyIncome());
            fp.setMonthlyExpenses(profile.getMonthlyExpenses());
            fp.setExistingLoanEmi(profile.getExistingLoanEmi());
            fp.setCreditScore(profile.getCreditScore());
            fp.setSavingsBalance(profile.getSavingsBalance());
            fp.setLastUpdatedAt(profile.getLastUpdatedAt());
            return repository.save(fp);
        }
        return null; // return null if not found
    }

    public Optional<FinancialProfile> getProfileById(Long id) {
        return repository.findById(id);
    }

    public List<FinancialProfile> getAllProfiles() {
        return repository.findAll();
    }

    public Optional<FinancialProfile> getProfileByUser(User user) {
        return repository.findByUser(user);
    }

    public boolean deleteProfile(Long id) {
        Optional<FinancialProfile> profile = repository.findById(id);
        if(profile.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
