package com.techelevator.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SavingsApi {

    private String type;
    @JsonProperty("electricity_unit")
    private String electricityUnit;
    @JsonProperty("electricity_value")
    private double electricityValue;
    private String country;

    private String state;

    public SavingsApi(){}

    public SavingsApi(String type, String country, String state, String electricityUnit, double electricityValue) {
        this.type = type;
        this.electricityUnit = electricityUnit;
        this.electricityValue = electricityValue;
        this.country = country;
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElectricityUnit() {
        return electricityUnit;
    }

    public void setElectricityUnit(String electricityUnit) {
        this.electricityUnit = electricityUnit;
    }

    public double getElectricityValue() {
        return electricityValue;
    }

    public void setElectricityValue(double electricityValue) {
        this.electricityValue = electricityValue;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SavingsApi{" +
                "type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", electricityUnit='" + electricityUnit + '\'' +
                ", electricityValue=" + electricityValue +
                '}';
    }
}
