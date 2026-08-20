package org.example.examen_poo.repository;

import org.example.examen_poo.model.CashFlow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class CashFlowRepository {

    private final List<CashFlow> cashFlows = new CopyOnWriteArrayList<>();

    public CashFlow save(CashFlow cashFlow) {
        cashFlows.add(cashFlow);
        return cashFlow;
    }

    public List<CashFlow> findAll() {
        return List.copyOf(cashFlows);
    }

    public List<CashFlow> findByUserId(String userId) {
        return cashFlows.stream()
                .filter(cf -> cf.getUserId().equals(userId))
                .toList();
    }
}