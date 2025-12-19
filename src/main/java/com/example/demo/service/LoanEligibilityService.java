package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;
import java.util.List;
import java.util.Optional;

public interface EligibilityResultService {
    EligibilityResult createResult(EligibilityResult result);
  
    List<EligibilityResult> getAllResults();
   
}
