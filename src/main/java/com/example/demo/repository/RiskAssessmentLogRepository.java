package com.example.demo.repository;

import com.example.demo.entity.RiskAssessmentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RiskAssessmentLogRepository extends JpaRepository<RiskAssessmentLog, Long> {
    Optional<RiskAssessmentLog> findByLoanRequestId(Long loanRequestId);
}
