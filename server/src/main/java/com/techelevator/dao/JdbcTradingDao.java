package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Trading;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTradingDao implements TradingDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTradingDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public Trading getTradeById(int tradeId) {
        Trading trade = null;

        String sql = "SELECT * FROM trades WHERE trade_id = ?;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, tradeId);
            if (result.next()) {
                trade = mapRowToTrade(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

        return trade;
    }
    @Override
    public List<Trading> getTrades() {
        List<Trading> allTrades = new ArrayList<>();
        String sql = "SELECT * FROM trades; ";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                allTrades.add(mapRowToTrade(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allTrades;
    }
    @Override
    public List<Trading> getAcceptedTrades() {
        List<Trading> acceptedTrades = new ArrayList<>();
        String sql = "SELECT * FROM trades " + "WHERE trade_status = 'accepted'; ";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                acceptedTrades.add(mapRowToTrade(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return acceptedTrades;
    }

    @Override
    public List<Trading> getTradeByOfferedByBusinessId(int offeredByBusinessId) {
        List<Trading> listOfTrades = new ArrayList<>();
        String sql = "SELECT * FROM trades WHERE offered_by_business_id = ?; ";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, offeredByBusinessId);
            while (results.next()) {
                listOfTrades.add(mapRowToTrade(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return listOfTrades;
    }

    @Override
    public List<Trading> getTradeByRequestedMaterialId(int requestedMaterialId) {
        List<Trading> requestedMaterial = new ArrayList<>();
        String sql = "SELECT * FROM trades " +
                "WHERE requested_material_id = ?; ";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, requestedMaterialId);
            while (results.next()) {
                requestedMaterial.add(mapRowToTrade(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return requestedMaterial;
    }
    @Override
    public Trading createTrade(Trading newTrade) {

        String sql = "INSERT INTO trades (offered_material_id, requested_material_id, offered_by_business_id, requested_to_business_id, trade_status, trade_date, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING trade_id;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, newTrade.getOfferedMaterialId(),
                    newTrade.getRequestedMaterialId(), newTrade.getOfferedByBusinessId(), newTrade.getRequestedToBusinessId(),
                    newTrade.getTradeStatus(), newTrade.getTradeDate(), newTrade.getCreatedBy());

            result.next();
            int project = result.getInt("trade_id");
            newTrade.setTradeId(project);

            return newTrade;


        } catch (DataIntegrityViolationException e){
            throw new DaoException(e.getMessage());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

    }

    @Override
    public Trading updateTrade(Trading updatedTrade) {

        Trading newTrade = null;

        String sql = "UPDATE trades SET offered_material_id = ?, requested_material_id = ?, offered_by_business_id = ?, " +
                "requested_to_business_id = ?, trade_status = ?, trade_date = ?, created_by = ? " +
                "WHERE trade_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, updatedTrade.getOfferedMaterialId(), updatedTrade.getRequestedMaterialId(),
                    updatedTrade.getOfferedByBusinessId(), updatedTrade.getRequestedToBusinessId(), updatedTrade.getTradeStatus(),
                    updatedTrade.getTradeDate(), updatedTrade.getCreatedBy(), updatedTrade.getTradeId());

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                newTrade = getTradeById(updatedTrade.getTradeId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newTrade;
    }

    @Override
    public int deleteTradeById(int tradeId) {

        int numberOfRows = 0;
        String sql = "DELETE FROM trades WHERE trade_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, tradeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;
    }

    private Trading mapRowToTrade(SqlRowSet rowSet) {


        Trading trade = new Trading(3, 6, 4, 1, 3, "accepted", "2023-09-15", null);


        trade.setTradeId(rowSet.getInt("trade_id"));
        trade.setOfferedMaterialId(rowSet.getInt("offered_material_id"));
        trade.setRequestedMaterialId(rowSet.getInt("requested_material_id"));
        trade.setOfferedByBusinessId(rowSet.getInt("offered_by_business_id"));
        trade.setRequestedToBusinessId(rowSet.getInt("requested_to_business_id"));
        trade.setTradeStatus(rowSet.getString("trade_status"));
        trade.setTradeDate(rowSet.getString("trade_date"));
        trade.setCreatedBy(rowSet.getString("created_by"));

        return trade;
    }
}
