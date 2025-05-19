package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcElectricityDao implements ElectricityDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcElectricityDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public double getElectricityUsageByMaterial(String materialName) {

        String sql = "SELECT avg_electricity_kwh_per_kg FROM electricity_usage WHERE material_name = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{materialName}, Double.class);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

    }
}
