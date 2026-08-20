package org.example.examen_poo.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Expense extends CashFlow {

    private String reason;
    private ExpenseFrequency frequency;

    public Expense() {}

    public Expense(String id, Instant createdAt, BigDecimal amount, String userId,
                   String reason, ExpenseFrequency frequency) {
        super(id, createdAt, amount, userId);
        this.reason = reason;
        this.frequency = frequency;
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