package com.example.demo.dto;

public class LoanDtos {

    public static class LoanRequestDto {
        public Double amount;
    }

    public static class EligibilityResponseDto {
        public boolean eligible;
        public Double maxAmount;
    }
}
