package org.example.examen_poo.repository;

import org.example.examen_poo.config.DatabaseConnection;
import org.example.examen_poo.model.CashFlow;
import org.example.examen_poo.model.Donation;
import org.example.examen_poo.model.Expense;
import org.example.examen_poo.model.ExpenseFrequency;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CashFlowRepository {

    public CashFlow save(CashFlow cashFlow) {
        String insertCashFlow = "INSERT INTO cash_flows (id, user_id, created_at, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(insertCashFlow)) {
                stmt.setString(1, cashFlow.getId());
                stmt.setString(2, cashFlow.getUserId());
                stmt.setTimestamp(3, Timestamp.from(cashFlow.getCreatedAt()));
                stmt.setBigDecimal(4, cashFlow.getAmount());
                stmt.executeUpdate();
            }

            if (cashFlow instanceof Donation donation) {
                String insertDonation = "INSERT INTO donations (id, comment) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertDonation)) {
                    stmt.setString(1, donation.getId());
                    stmt.setString(2, donation.getComment());
                    stmt.executeUpdate();
                }
            } else if (cashFlow instanceof Expense expense) {
                String insertExpense = "INSERT INTO expenses (id, reason, frequency) VALUES (?, ?, ?::expense_frequency)";
                try (PreparedStatement stmt = conn.prepareStatement(insertExpense)) {
                    stmt.setString(1, expense.getId());
                    stmt.setString(2, expense.getReason());
                    stmt.setString(3, expense.getFrequency().name());
                    stmt.executeUpdate();
                }
            }

            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du cash flow", e);
        }

        return cashFlow;
    }

    public List<CashFlow> findAll() {
        String sql = """
                SELECT cf.id, cf.user_id, cf.created_at, cf.amount,
                       d.comment,
                       e.reason, e.frequency
                FROM cash_flows cf
                LEFT JOIN donations d ON d.id = cf.id
                LEFT JOIN expenses e ON e.id = cf.id
                """;

        List<CashFlow> result = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recuperation des cash flows", e);
        }

        return result;
    }

    public List<CashFlow> findByUserId(String userId) {
        String sql = """
                SELECT cf.id, cf.user_id, cf.created_at, cf.amount,
                       d.comment,
                       e.reason, e.frequency
                FROM cash_flows cf
                LEFT JOIN donations d ON d.id = cf.id
                LEFT JOIN expenses e ON e.id = cf.id
                WHERE cf.user_id = ?
                """;

        List<CashFlow> result = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recuperation des cash flows de l'utilisateur", e);
        }

        return result;
    }

    private CashFlow mapRow(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String userId = rs.getString("user_id");
        Instant createdAt = rs.getTimestamp("created_at").toInstant();
        var amount = rs.getBigDecimal("amount");

        String comment = rs.getString("comment");
        String reason = rs.getString("reason");
        String frequencyStr = rs.getString("frequency");

        if (reason != null) {
            ExpenseFrequency frequency = frequencyStr != null
                    ? ExpenseFrequency.valueOf(frequencyStr)
                    : ExpenseFrequency.NONE;
            return new Expense(id, createdAt, amount, userId, reason, frequency);
        } else {
            return new Donation(id, createdAt, amount, userId, comment);
        }
    }
}