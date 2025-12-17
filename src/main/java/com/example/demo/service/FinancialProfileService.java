package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface FinancialProfileService {
    FinancialProfile createProfile(FinancialProfile profile);
    FinancialProfile updateProfile(Long id, FinancialProfile profile);
    Optional<FinancialProfile> getProfileById(Long id);
    List<FinancialProfile> getAllProfiles();
    Optional<FinancialProfile> getProfileByUser(User user);
    boolean deleteProfile(Long id);  // simple delete
}
