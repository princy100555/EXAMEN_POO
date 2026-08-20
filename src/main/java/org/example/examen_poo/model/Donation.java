package org.example.examen_poo.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Donation extends CashFlow {

    private String comment;

    public Donation() {}

    public Donation(String id, Instant createdAt, BigDecimal amount, String userId, String comment) {
        super(id, createdAt, amount, userId);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}