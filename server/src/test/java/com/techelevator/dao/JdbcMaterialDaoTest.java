package com.techelevator.dao;

import com.techelevator.model.Material;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcMaterialDaoTest extends BaseDaoTest{

    private final Material MATERIAL_1 = new Material(3, 1, "Plexiglass", "Excellent", 1785.34, 25.50, true, null);

    private final Material MATERIAL_2 = new Material(5, 3, "Brass", "New", 1159.73, 11.44, false, null);

    private final Material MATERIAL_3 = new Material(7, 1, "Metal", "Old", 309.50, 25.60, true, null);

    private JdbcMaterialDao sut;
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    public void setup() {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.sut = new JdbcMaterialDao(jdbcTemplate);
    }

    @Test
    public void getMaterialById_returns_correct_Material(){

        Material material = sut.getMaterialById(3);
        assertMaterialsMatch(MATERIAL_1, material);

        material = sut.getMaterialById(5);
        assertMaterialsMatch(MATERIAL_2, material);

        material = sut.getMaterialById(7);
        assertMaterialsMatch(MATERIAL_3, material);

    }

    @Test
    public void createMaterial_creates_new_material() {

        Material testMaterial = new Material();

        testMaterial.setBusinessId(1);
        testMaterial.setMaterialName("NoName");
        testMaterial.setCondition("Good");
        testMaterial.setPrice(1200.00);
        testMaterial.setQuantityKg(12.67);
        testMaterial.setAvailableForTrading(true);
        testMaterial.setCreatedBy("Gaby");


        Material materialCreated = sut.createMaterial(testMaterial);

        int newId = materialCreated.getMaterialId();
        Assertions.assertTrue(newId > 0);

        Material resultMaterial = sut.getMaterialById(newId);
        assertMaterialsMatch(materialCreated, resultMaterial);

    }

    @Test
    public void updateMaterial_returns_correct_values(){
        Material createdMaterial = sut.getMaterialById(3);

        createdMaterial.setBusinessId(1);
        createdMaterial.setMaterialName("Test");
        createdMaterial.setCondition("Good");
        createdMaterial.setPrice(3000);
        createdMaterial.setQuantityKg(2.0);
        createdMaterial.setAvailableForTrading(true);
        createdMaterial.setCreatedBy(null);

        sut.updateMaterial(createdMaterial);

        Material materialUpdated = sut.getMaterialById(3);

        assertMaterialsMatch(createdMaterial, materialUpdated);
    }

    @Test
    public void deleteMaterialById_deletes_timesheet() {

        int rowsAffected = sut.deleteMaterialById(3);
        assertEquals(1, rowsAffected);
        Material materialResult = sut.getMaterialById(3);
        Assertions.assertNull(materialResult);


        rowsAffected = sut.deleteMaterialById(5);
        assertEquals(1, rowsAffected);
        materialResult = sut.getMaterialById(5);
        Assertions.assertNull(materialResult);

        rowsAffected = sut.deleteMaterialById(7);
        assertEquals(1, rowsAffected);
        materialResult = sut.getMaterialById(7);
        Assertions.assertNull(materialResult);


    }


    private void assertMaterialsMatch(Material expected, Material actual) {

        Assertions.assertEquals(expected.getMaterialId(), actual.getMaterialId());
        Assertions.assertEquals(expected.getBusinessId(), actual.getBusinessId());
        Assertions.assertEquals(expected.getMaterialName(), actual.getMaterialName());
        Assertions.assertEquals(expected.getCondition(), actual.getCondition());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getQuantityKg(), actual.getQuantityKg());
        Assertions.assertEquals(expected.isAvailableForTrading(), actual.isAvailableForTrading());
        Assertions.assertEquals(expected.getCreatedBy(), actual.getCreatedBy());
    }


}
