package com.example.demo.repository;

import com.example.demo.entity.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialProfileRepository extends JpaRepository<FinancialProfile, Long> {

    // Your existing methods
    Optional<FinancialProfile> findTopByUserIdOrderByCreatedAtDesc(Long userId);

    // ✅ Add this for the test
    default Optional<FinancialProfile> findByUserId(Long userId) {
        return findTopByUserIdOrderByCreatedAtDesc(userId);
    }
}
