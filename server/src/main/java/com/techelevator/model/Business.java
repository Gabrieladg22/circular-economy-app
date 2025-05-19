package com.techelevator.model;

import jakarta.validation.constraints.NotNull;

public class Business {

    private int businessId;
    @NotNull
    private String businessName;
    @NotNull
    private String businessType;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;

    public Business(){}

    public Business(int businessId, String businessName, String businessType, String city, String state, String country) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessType = businessType;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString() {
        return "Business{" +
                "businessId=" + businessId +
                ", businessName='" + businessName + '\'' +
                ", businessType='" + businessType + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
