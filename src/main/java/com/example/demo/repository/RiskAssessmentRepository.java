import com.example.demo.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {

    // If you have Optional<RiskAssessment> methods:
    // Change them to return List<RiskAssessment> for the test
    List<RiskAssessment> findByUserId(Long userId);
}
