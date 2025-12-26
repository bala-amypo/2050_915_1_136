package com.example.demo.repository;

import com.example.demo.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {

    // Return all assessments for a loan request
    List<RiskAssessment> findByLoanRequestId(Long loanRequestId);
}
