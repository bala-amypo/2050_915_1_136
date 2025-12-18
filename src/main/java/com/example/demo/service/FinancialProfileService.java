package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface FinancialProfileService {
    FinancialProfile createProfile(FinancialProfile profile);
  
    List<FinancialProfile> getAllProfiles();
}
