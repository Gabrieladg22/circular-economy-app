
package com.techelevator.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attributes {

    private String country;
    private String state;
    private String electricityUnit;
    private Double electricityValue;
    private String estimatedAt;
    private Integer carbonG;
    private Double carbonLb;
    @JsonProperty("carbon_kg")
    private Double carbonKg;
    private Double carbonMt;

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

    public String getElectricityUnit() {
        return electricityUnit;
    }

    public void setElectricityUnit(String electricityUnit) {
        this.electricityUnit = electricityUnit;
    }

    public Double getElectricityValue() {
        return electricityValue;
    }

    public void setElectricityValue(Double electricityValue) {
        this.electricityValue = electricityValue;
    }

    public String getEstimatedAt() {
        return estimatedAt;
    }

    public void setEstimatedAt(String estimatedAt) {
        this.estimatedAt = estimatedAt;
    }

    public Integer getCarbonG() {
        return carbonG;
    }

    public void setCarbonG(Integer carbonG) {
        this.carbonG = carbonG;
    }

    public Double getCarbonLb() {
        return carbonLb;
    }

    public void setCarbonLb(Double carbonLb) {
        this.carbonLb = carbonLb;
    }

    public Double getCarbonKg() {
        return carbonKg;
    }

    public void setCarbonKg(Double carbonKg) {
        this.carbonKg = carbonKg;
    }

    public Double getCarbonMt() {
        return carbonMt;
    }

    public void setCarbonMt(Double carbonMt) {
        this.carbonMt = carbonMt;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", electricityUnit='" + electricityUnit + '\'' +
                ", electricityValue=" + electricityValue +
                ", estimatedAt='" + estimatedAt + '\'' +
                ", carbonG=" + carbonG +
                ", carbonLb=" + carbonLb +
                ", carbonKg=" + carbonKg +
                ", carbonMt=" + carbonMt +
                '}';
    }
}
