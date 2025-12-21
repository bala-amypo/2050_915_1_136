import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;

import java.util.Optional;

public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository repository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<RiskAssessment> getByLoanRequestId(Long loanRequestId) {
        return repository.findByLoanRequestId(loanRequestId);
    }
}
