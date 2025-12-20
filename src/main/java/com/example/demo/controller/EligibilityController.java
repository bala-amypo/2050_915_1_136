import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/{userId}")
    public EligibilityResult check(@PathVariable Long userId) {
        return eligibilityService.checkEligibility(userId);
    }
}
