package com.example.demo.repository;

import com.example.demo.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

    // ✅ ADD THIS METHOD
    List<LoanRequest> findByUserId(Long userId);
}
