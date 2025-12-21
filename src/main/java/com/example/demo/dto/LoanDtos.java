package com.example.demo.dto;

public class LoanDtos {

    // ✅ DTO for creating loan
    public static class LoanRequestDto {

        private Double requestedAmount;
        private Integer tenureMonths;
        private Long userId;

        public Double getRequestedAmount() {
            return requestedAmount;
        }

        public void setRequestedAmount(Double requestedAmount) {
            this.requestedAmount = requestedAmount;
        }

        public Integer getTenureMonths() {
            return tenureMonths;
        }

        public void setTenureMonths(Integer tenureMonths) {
            this.tenureMonths = tenureMonths;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    // (optional – keep if you use eligibility)
    public static class EligibilityResponseDto {
        public boolean eligible;
        public Double maxAmount;
    }
}
