package com.techelevator.dao;

import com.techelevator.model.Business;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcBusinessDaoTest extends BaseDaoTest{

    private final Business BUSINESS_1 = new Business(1, "Photobug", "Industrial Specialties", "Huntington", "West Virginia", "US");
    private final Business BUSINESS_2 = new Business(3, "Skipfire", "Railroads", "Los Angeles", "California", "US");
    private final Business BUSINESS_3 = new Business(5, "Talane", "Savings Institutions", "Fort Lauderdale", "Florida", "US");
    private JdbcBusinessDao sut;
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    public void setup() {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.sut = new JdbcBusinessDao(jdbcTemplate);
    }

    @Test
    public void getBusinessById_returns_correct_business() {
        Business business = sut.getBusinessById(1);
        assertBusinessesMatch(BUSINESS_1, business);

        business = sut.getBusinessById(3);
        assertBusinessesMatch(BUSINESS_2, business);

        business = sut.getBusinessById(5);
        assertBusinessesMatch(BUSINESS_3, business);

    }

    @Test
    public void getBusinessById_returns_null_when_id_not_found() {
        Business business = sut.getBusinessById(-99);
        Assertions.assertNull(business);
    }

    @Test
    public void getBusinesses_returns_all_businesses_in_database() {

        List<Business> businesses = sut.getBusinesses();
        Assertions.assertEquals(5, businesses.size());

        assertBusinessesMatch(BUSINESS_1, businesses.get(0));
        assertBusinessesMatch(BUSINESS_2, businesses.get(2));
        assertBusinessesMatch(BUSINESS_3, businesses.get(4));

    }

    @Test
    public void createBusiness_returns_business_with_id_and_expected_values() {


//        Business newBusiness = new Business(savingsId, businessName, materialName, quantityKg, electricityUsedKwh, co2SavingsKg);

        Business newBusiness = new Business();


        newBusiness.setBusinessName("Test");
        newBusiness.setBusinessType("NoBusiness");
        newBusiness.setCity("Chicago");
        newBusiness.setState("Illinois");
        newBusiness.setCountry("US");

        Business createdBusiness = sut.createBusiness(newBusiness);

        int newId = createdBusiness.getBusinessId();
        Assertions.assertTrue(newId > 0);

        Business retrievedBusiness = sut.getBusinessById(newId);
        assertBusinessesMatch(createdBusiness, retrievedBusiness);
    }

    @Test
    public void updateBusiness_returns_correct_values(){
        Business nuevo = sut.getBusinessById(1);

        nuevo.setBusinessName("Test");
        nuevo.setBusinessType("NoBusiness");
        nuevo.setCity("Chicago");
        nuevo.setState("Illinois");

        sut.updateBusiness(nuevo);

        Business businessUpdated = sut.getBusinessById(1);

        assertBusinessesMatch(nuevo, businessUpdated);
    }

    @Test
    public void deleteBusinessById_deletes_timesheet() {

        int rowsAffected = sut.deleteBusinessById(1);
        assertEquals(1, rowsAffected);
        Business businessResult = sut.getBusinessById(1);
        Assertions.assertNull(businessResult);


        rowsAffected = sut.deleteBusinessById(3);
        assertEquals(1, rowsAffected);
        businessResult = sut.getBusinessById(3);
        Assertions.assertNull(businessResult);

        rowsAffected = sut.deleteBusinessById(5);
        assertEquals(1, rowsAffected);
        businessResult = sut.getBusinessById(5);
        Assertions.assertNull(businessResult);


    }

    private void assertBusinessesMatch(Business expected, Business actual) {
        Assertions.assertEquals(expected.getBusinessId(), actual.getBusinessId());
        Assertions.assertEquals(expected.getBusinessName(), actual.getBusinessName());
        Assertions.assertEquals(expected.getBusinessType(), actual.getBusinessType());
        Assertions.assertEquals(expected.getCity(), actual.getCity());
        Assertions.assertEquals(expected.getState(), actual.getState());
        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
    }
}
