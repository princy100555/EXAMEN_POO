package org.example.examen_poo.service;

import org.example.examen_poo.model.*;
import org.example.examen_poo.repository.CashFlowRepository;
import org.example.examen_poo.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CashFlowService {

    private final CashFlowRepository cashFlowRepository;
    private final UserRepository userRepository;

    public CashFlowService(CashFlowRepository cashFlowRepository, UserRepository userRepository) {
        this.cashFlowRepository = cashFlowRepository;
        this.userRepository = userRepository;
    }

    public List<CashFlow> getCashFlows(String type) {
        List<CashFlow> all = cashFlowRepository.findAll();
        if (type == null) {
            return all;
        }
        return switch (type.toLowerCase()) {
            case "donation" -> all.stream().filter(cf -> cf instanceof Donation).toList();
            case "expense" -> all.stream().filter(cf -> cf instanceof Expense).toList();
            default -> throw new IllegalArgumentException("Type inconnu : " + type);
        };
    }

    public List<CashFlow> getCashFlowsByUser(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable : " + userId));
        return cashFlowRepository.findByUserId(userId);
    }

    public Expense createExpense(String userId, BigDecimal amount, String reason, ExpenseFrequency frequency) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable : " + userId));

        Expense expense = new Expense(
                UUID.randomUUID().toString(),
                Instant.now(),
                amount,
                userId,
                reason,
                frequency
        );
        cashFlowRepository.save(expense);
        return expense;
    }

    public BigDecimal getBalance() {
        return cashFlowRepository.findAll().stream()
                .map(cf -> cf instanceof Donation ? cf.getAmount() : cf.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}