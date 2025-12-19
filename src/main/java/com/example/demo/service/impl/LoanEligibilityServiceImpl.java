package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.service.EligibilityResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EligibilityResultServiceImpl implements EligibilityResultService {

    @Autowired
    private EligibilityResultRepository repository;

    @Override
    public EligibilityResult createResult(EligibilityResult result) {
        return repository.save(result);
    }

   
    @Override
    public List<EligibilityResult> getAllResults() {
        return repository.findAll();
    }

   
}
