package com.example.demo.repository;

import com.example.demo.entity.RiskAssessmentL;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessmentLog, Long> {
    List<RiskAssessmentLog> findByLoanRequestId(Long loanRequestId);
}
