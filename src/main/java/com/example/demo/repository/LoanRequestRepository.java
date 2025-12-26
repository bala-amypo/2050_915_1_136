package com.example.demo.repository;

import com.example.demo.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

    // Find all loan requests for a user
    List<LoanRequest> findByUser_Id(Long userId);

}
