package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;

import java.util.List;

public interface RiskAssessmentService {

    List<RiskAssessment> getByLoanRequestId(Long loanRequestId);
}
