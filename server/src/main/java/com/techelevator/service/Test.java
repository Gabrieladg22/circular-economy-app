package com.techelevator.service;

import com.techelevator.model.api.CarbonSavingsApi;

public class Test {

    public static void main(String[] args) {
        RestCarbonSavingsService service = new RestCarbonSavingsService();


        CarbonSavingsApi api = service.getCarbonEmissions("electricity", "US", "FL",  "kwh",  45.1);
        System.out.println(api.getData().getAttributes().getCarbonKg());
    }
}
