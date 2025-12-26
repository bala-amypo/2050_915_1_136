package com.example.demo.dto;

import com.example.demo.entity.User;

public class LoanDtos {

    public static class LoanRequestDto {

        private Double requestedAmount;
        private Integer tenureMonths;
        private User user; // Required by service

        // ---------- Getters & Setters ----------

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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
