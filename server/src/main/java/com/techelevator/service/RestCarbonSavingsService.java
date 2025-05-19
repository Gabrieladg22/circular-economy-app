package com.techelevator.service;


import com.techelevator.model.api.CarbonSavingsApi;
import com.techelevator.model.api.SavingsApi;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;


import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;


@Component
public class RestCarbonSavingsService implements CarbonSavingsService{

    private final String BASE_URL = "https://www.carboninterface.com/api/v1/estimates";
    private RestClient restClient = RestClient.create(BASE_URL);

    private static final String API_KEY = "jnOLgTUeD0LWC2tNJOOBw";

    @Override
    public CarbonSavingsApi getCarbonEmissions(String type, String country, String state, String electricityUnit,
                                               double electricityValue) {

        SavingsApi newApi = new SavingsApi();
        newApi.setElectricityValue(electricityValue);
        newApi.setCountry(country);
        newApi.setState(state);
        newApi.setElectricityUnit(electricityUnit);
        newApi.setType(type);

        CarbonSavingsApi response = null;

        try {
            response = restClient.post()
                    .header("Authorization", "Bearer " + API_KEY)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(newApi)
                    .retrieve()
                    .body(CarbonSavingsApi.class);


        } catch (RestClientResponseException e) {
            System.out.println(e);

        } catch (ResourceAccessException e) {
            System.out.println(e);
        }

        return response;
    }

}

