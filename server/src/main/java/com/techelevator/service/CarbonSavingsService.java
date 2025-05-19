package com.techelevator.service;

import com.techelevator.model.api.CarbonSavingsApi;


public interface CarbonSavingsService {

    CarbonSavingsApi getCarbonEmissions(String type, String country, String state, String electricityUnit, double electricityValue);
}