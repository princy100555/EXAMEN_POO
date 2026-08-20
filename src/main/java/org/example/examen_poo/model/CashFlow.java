package org.example.examen_poo.model;

import java.math.BigDecimal;
import java.time.Instant;

public abstract class CashFlow {

    private String id;
    private Instant createdAt;
    private BigDecimal amount;
    private String userId;

    public CashFlow() {}

    public CashFlow(String id, Instant createdAt, BigDecimal amount, String userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.amount = amount;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}