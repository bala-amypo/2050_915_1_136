package com.example.demo.repository;

import com.example.demo.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskAssessmentLogRepository
        extends JpaRepository<RiskAssessmentLog, Long> {

    List<RiskAssessmentLog> findByLoanRequestId(Long loanRequestId);
}
