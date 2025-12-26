package com.example.demo.dto;

public class LoanDtos {

    public static class LoanRequestDto {
        private Long userId;
        private Double requestedAmount;
        private Integer tenureMonths;

        public LoanRequestDto() {}

        public LoanRequestDto(Long userId, Double requestedAmount, Integer tenureMonths) {
            this.userId = userId;
            this.requestedAmount = requestedAmount;
            this.tenureMonths = tenureMonths;
        }

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }

        public Double getRequestedAmount() { return requestedAmount; }
        public void setRequestedAmount(Double requestedAmount) { this.requestedAmount = requestedAmount; }

        public Integer getTenureMonths() { return tenureMonths; }
        public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }
    }
}
