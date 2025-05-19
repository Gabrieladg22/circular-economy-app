package com.techelevator.service;

import com.techelevator.dao.BusinessDao;
import com.techelevator.dao.CarbonSavingsDao;
import com.techelevator.dao.ElectricityDao;
import com.techelevator.model.Business;
import com.techelevator.model.api.CarbonSavingsApi;
import org.springframework.stereotype.Component;

@Component
public class MaterialService {

    private final ElectricityDao electricityDao;
    private final CarbonSavingsDao carbonSavingsDao;
    private final RestCarbonSavingsService restCarbonSavingsService;
    private final BusinessDao businessDao;

    public MaterialService(ElectricityDao electricityDao, CarbonSavingsDao carbonSavingsDao,
                           RestCarbonSavingsService restCarbonSavingsService, BusinessDao businessDao) {
        this.electricityDao = electricityDao;
        this.carbonSavingsDao = carbonSavingsDao;
        this.restCarbonSavingsService = restCarbonSavingsService;
        this.businessDao = businessDao;
    }

    public double addMaterialAndCalculateSavings(int businessId, String materialName, double quantityKg) {

        double avgElectricityUsage = electricityDao.getElectricityUsageByMaterial(materialName);
        double electricityUsedPerKg = avgElectricityUsage * quantityKg;

        if (avgElectricityUsage <= 0) {
            throw new RuntimeException("Electricity Usage Data not found for material: " + materialName);
        }

        Business retrieveBusiness = businessDao.getBusinessById(businessId);
        if(retrieveBusiness == null) {
            throw new RuntimeException("Business not found for ID: " + businessId);
        }
        String businessName = retrieveBusiness.getBusinessName();

        CarbonSavingsApi carbonSavingsKg = restCarbonSavingsService.getCarbonEmissions("electricity",
                retrieveBusiness.getCountry(), retrieveBusiness.getState(), "kwh", electricityUsedPerKg);

        double savings = carbonSavingsKg.getData().getAttributes().getCarbonKg();

        carbonSavingsDao.saveCarbonSavings(businessName, materialName, quantityKg, electricityUsedPerKg, savings);

        return savings;

    }
}
