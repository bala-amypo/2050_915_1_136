package com.example.demo.repository;

import com.example.demo.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

    // Notice the underscore: findByUser_Id
    List<LoanRequest> findByUser_Id(Long userId);
}
