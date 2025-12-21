@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    private final LoanRequestService loanRequestService;

    public LoanRequestController(LoanRequestService loanRequestService) {
        this.loanRequestService = loanRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<LoanRequest> createLoan(
            @RequestBody LoanDtos dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanRequestService.createLoanRequest(dto));
    }
}
