package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Savings;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCarbonSavingsDao implements CarbonSavingsDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCarbonSavingsDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCarbonSavings(String businessName, String materialName, double quantityKg, double electricityUsed,
                                  double carbonSavings) {

        String sql = "INSERT INTO carbon_savings (business_name, material_name, quantity_kg, electricity_used_kwh, " +
                "co2_savings_kg) VALUES (?, ?, ?, ?, ?);";

        jdbcTemplate.update(sql, businessName, materialName, quantityKg, electricityUsed, carbonSavings);
    }

    @Override
    public List<Savings> getAllSavings() {
        List<Savings> allSavings = new ArrayList<>();

        String sql = "SELECT * FROM carbon_savings;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                allSavings.add(mapRowToSavings(result));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

        return allSavings;
    }

    @Override
    public Savings getSavingsById(int savingsId) {
        Savings savings = null;

        String sql = "SELECT * FROM carbon_savings WHERE savings_id = ?;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, savingsId);
            if (result.next()) {
                savings = mapRowToSavings(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

        return savings;
    }

    @Override
    public List<Savings> getSavingsByBusiness(String businessName) {
        List<Savings> savingsByBusiness = new ArrayList<>();

        String sql = "SELECT * FROM carbon_savings WHERE business_name = ?;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, businessName);
            while (result.next()) {
                savingsByBusiness.add(mapRowToSavings(result));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed", e);
        }

        return savingsByBusiness;
    }

    @Override
    public List<Savings> getSavingsByMaterial(String materialName) {

        List<Savings> savingsByMaterial = new ArrayList<>();

        String sql = "SELECT * FROM carbon_savings WHERE material_name = ?";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, materialName);
            while (result.next()) {
                savingsByMaterial.add(mapRowToSavings(result));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed", e);
        }

        return savingsByMaterial;
    }

    private Savings mapRowToSavings(SqlRowSet rowSet) {

        int savingsId = rowSet.getInt ("savings_id");
        String businessName = rowSet.getString("business_name");
        String materialName = rowSet.getString("material_name");
        double quantityKg = rowSet.getDouble("quantity_kg");
        double electricityUsedKwh = rowSet.getDouble("electricity_used_kwh");
        double co2SavingsKg = rowSet.getDouble("co2_savings_kg");

        Savings savings = new Savings(savingsId, businessName, materialName, quantityKg, electricityUsedKwh,
                co2SavingsKg);

        return savings;
    }

}
