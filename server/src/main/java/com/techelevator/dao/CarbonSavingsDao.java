package com.techelevator.dao;


import com.techelevator.model.Savings;
import java.util.List;
import com.techelevator.model.api.SavingsApi;

public interface CarbonSavingsDao {

    void saveCarbonSavings(String businessName, String materialName, double quantityKg, double electricityUsed,
                           double carbonSavings);


    List<Savings> getAllSavings();

    Savings getSavingsById(int savingsId);

    List<Savings> getSavingsByBusiness(String businessName);

    List<Savings> getSavingsByMaterial(String materialName);



}
