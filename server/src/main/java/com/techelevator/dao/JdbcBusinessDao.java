package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Business;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcBusinessDao implements BusinessDao{

    private JdbcTemplate jdbcTemplate;


    public JdbcBusinessDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public Business getBusinessById(int businessId) {
        Business business = null;

        String sql = "SELECT * FROM business WHERE business_id = ?;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, businessId);
            if (result.next()) {
                business = mapRowToBusiness(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

        return business;
    }

    @Override
    public List<Business> getBusinesses() {
        List<Business> allBusinesses = new ArrayList<>();

        String sql = "SELECT * FROM business;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                allBusinesses.add(mapRowToBusiness(result));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

        return allBusinesses;
    }

    @Override
    public Business createBusiness(Business newBusiness) {

        Integer newId;
        Business businessNew = null;

        String sql = "INSERT INTO business (business_name, business_type, city, state, country) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING business_id;";

        try {
            newId = jdbcTemplate.queryForObject(sql, Integer.class, newBusiness.getBusinessName(),
                    newBusiness.getBusinessType(), newBusiness.getCity(), newBusiness.getState(), newBusiness.getCountry());


            businessNew = getBusinessById(newId);
        } catch (DataIntegrityViolationException e){
            throw new DaoException(e.getMessage());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return businessNew;

    }

    @Override
    public Business updateBusiness(Business updatedBusiness) {
        String sql = "UPDATE business SET business_name = ?, business_type = ?, city = ?, state = ?, country = ? " +
                "WHERE business_id = ?;";

        try {

            int newRow = jdbcTemplate.update(sql, updatedBusiness.getBusinessName(), updatedBusiness.getBusinessType(),
                    updatedBusiness.getCity(), updatedBusiness.getState(), updatedBusiness.getCountry(),
                    updatedBusiness.getBusinessId());

            return updatedBusiness;
        } catch (DataIntegrityViolationException e){
            throw new DaoException(e.getMessage());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public int deleteBusinessById(int id) {

        int rowToDelete = 0;
        int rowToDelete1 = 0;
        int rowToDelete2 = 0;

        String sql = "DELETE FROM trades WHERE offered_by_business_id = ? OR requested_to_business_id = ?;";
        String newSql = "DELETE FROM material WHERE business_id = ?;";
        String sql2 = "DELETE FROM business WHERE business_id = ?;";
        try {

            rowToDelete = jdbcTemplate.update(sql, id, id);
            rowToDelete1 = jdbcTemplate.update(newSql, id);
            rowToDelete2 = jdbcTemplate.update(sql2, id);


        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException(e.getMessage());
        }
        return rowToDelete2;

    }

    private Business mapRowToBusiness(SqlRowSet rowSet) {

        int businessId = rowSet.getInt ("business_id");
        String businessName = rowSet.getString("business_name");
        String businessType = rowSet.getString("business_type");
        String city = rowSet.getString("city");
        String state = rowSet.getString("state");
        String country = rowSet.getString("country");

        Business business = new Business(businessId, businessName, businessType, city, state, country);

        return business;
    }

}
