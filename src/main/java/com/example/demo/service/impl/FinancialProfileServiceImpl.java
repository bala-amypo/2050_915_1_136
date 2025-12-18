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
    public List<FinancialProfile> getAllProfiles() {
        return repository.findAll();
    }

   
}
