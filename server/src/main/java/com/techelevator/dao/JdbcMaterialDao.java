package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaterialDao implements MaterialDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMaterialDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

    }
    @Override
    public Material getMaterialById(int materialId) {

        Material material = null;

        String sql = "SELECT * FROM material WHERE material_id = ?;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, materialId);
            if (result.next()) {
                material = mapRowToMaterial(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

        return material;
    }

    @Override
    public List<Material> getMaterials() {

        List<Material> allMaterials = new ArrayList<>();

        String sql = "SELECT * FROM material;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                allMaterials.add(mapRowToMaterial(result));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed");
        }

        return allMaterials;
    }

    @Override
    public List<Material> getMaterialsAvailableForTrading() {

        List<Material> availableMaterials = new ArrayList<>();

        String sql = "SELECT * FROM material WHERE is_available_for_trading = true;";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                availableMaterials.add(mapRowToMaterial(result));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Connection to DataBase failed", e);
        }

        return availableMaterials;
    }

    @Override
    public Material createMaterial(Material newMaterial){

        Material newestMaterial = null;

        String sql = "INSERT INTO material (business_id, material_name, condition, price, quantity_kg, is_available_for_trading, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING material_id;";

        try {

            int materialNewId = jdbcTemplate.queryForObject(sql, int.class, newMaterial.getBusinessId(),
                    newMaterial.getMaterialName(), newMaterial.getCondition(),
                    newMaterial.getPrice(), newMaterial.getQuantityKg(), newMaterial.isAvailableForTrading(),
                    newMaterial.getCreatedBy());


            newestMaterial = getMaterialById(materialNewId);


        } catch (DataIntegrityViolationException e){
            throw new DaoException(e.getMessage());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return newestMaterial;
    }

    @Override
    public Material updateMaterial(Material updatedMaterial) {

        try {
            String sql = "UPDATE material SET business_id = ?, material_name = ?, condition = ?, price = ?, " +
                    "quantity_kg = ?, is_available_for_trading = ?, created_by = ? WHERE material_id = ?;";

            int newRow = jdbcTemplate.update(sql, updatedMaterial.getBusinessId(), updatedMaterial.getMaterialName(),
                    updatedMaterial.getCondition(), updatedMaterial.getPrice(), updatedMaterial.getQuantityKg(),
                    updatedMaterial.isAvailableForTrading(), updatedMaterial.getCreatedBy(),
                    updatedMaterial.getMaterialId());

            return updatedMaterial;

        } catch (DataIntegrityViolationException e){
            throw new DaoException(e.getMessage());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public int deleteMaterialById(int id) {

        int rowToDelete = 0;
        int rowToDelete3 = 0;

        String sql = "DELETE FROM trades WHERE offered_material_id = ? OR requested_material_id = ?;";
        String sql2 = "DELETE FROM material WHERE material_id = ?;";
        try {

            rowToDelete = jdbcTemplate.update(sql, id, id);
            rowToDelete3 = jdbcTemplate.update(sql2, id);


        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException(e.getMessage());
        }
        return rowToDelete3;

    }

    private Material mapRowToMaterial(SqlRowSet rowSet) {

        int materialId = rowSet.getInt("material_id");
        int businessId = rowSet.getInt("business_id");
        String materialName = rowSet.getString("material_name");
        String condition = rowSet.getString("condition");
        double price = rowSet.getDouble("price");
        double quantityKg = rowSet.getDouble("quantity_kg");
        boolean isAvailableForTrading = rowSet.getBoolean("is_available_for_trading");
        String createdBy = rowSet.getString("created_by");

        Material material = new Material(materialId, businessId, materialName, condition, price, quantityKg,
                isAvailableForTrading, createdBy);

        return material;
    }
}
