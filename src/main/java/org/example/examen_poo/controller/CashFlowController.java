package org.example.examen_poo.controller;

import org.example.examen_poo.model.CashFlow;
import org.example.examen_poo.model.Expense;
import org.example.examen_poo.service.CashFlowService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CashFlowController {

    private final CashFlowService cashFlowService;

    public CashFlowController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }

    @GetMapping("/cash-flows")
    public List<CashFlow> getCashFlows(@RequestParam(required = false) String type) {
        return cashFlowService.getCashFlows(type);
    }

    @GetMapping("/users/{id}/cash-flows")
    public List<CashFlow> getCashFlowsByUser(@PathVariable String id) {
        return cashFlowService.getCashFlowsByUser(id);
    }

    @PostMapping("/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public Expense createExpense(@RequestBody ExpenseRequest request) {
        return cashFlowService.createExpense(
                request.getUserId(),
                request.getAmount(),
                request.getReason(),
                request.getFrequency()
        );
    }

    @GetMapping("/balance")
    public BigDecimal getBalance() {
        return cashFlowService.getBalance();
    }
}