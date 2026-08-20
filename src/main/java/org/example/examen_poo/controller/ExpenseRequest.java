package org.example.examen_poo.controller;

import org.example.examen_poo.model.ExpenseFrequency;
import java.math.BigDecimal;

public class ExpenseRequest {

    private String userId;
    private BigDecimal amount;
    private String reason;
    private ExpenseFrequency frequency;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ExpenseFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(ExpenseFrequency frequency) {
        this.frequency = frequency;
    }
}