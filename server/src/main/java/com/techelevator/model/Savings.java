package com.techelevator.model;

import jakarta.validation.constraints.NotNull;

public class Savings {

    private int savingsId;
    @NotNull
    private String businessName;
    @NotNull
    private String materialName;
    @NotNull
    double quantityKg;
    @NotNull
    double electricityUsed;
    @NotNull
    double carbonSavings;

    public Savings(){}

    public Savings(int savingsId, String businessName, String materialName, double quantityKg, double electricityUsedKwh,
                   double co2SavingsKg) {
        this.savingsId = savingsId;
        this.businessName = businessName;
        this.materialName = materialName;
        this.quantityKg = quantityKg;
        this.electricityUsed = electricityUsedKwh;
        this.carbonSavings = co2SavingsKg;
    }

    public int getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(int savingsId) {
        this.savingsId = savingsId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getQuantityKg() {
        return quantityKg;
    }

    public void setQuantityKg(double quantityKg) {
        this.quantityKg = quantityKg;
    }

    public double getElectricityUsed() {
        return electricityUsed;
    }

    public void setElectricityUsed(double electricityUsed) {
        this.electricityUsed = electricityUsed;
    }

    public double getCarbonSavings() {
        return carbonSavings;
    }

    public void setCarbonSavings(double carbonSavings) {
        this.carbonSavings = carbonSavings;
    }

    @Override
    public String toString() {
        return "Savings{" +
                "savingsId=" + savingsId +
                ", businessName='" + businessName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", quantityKg=" + quantityKg +
                ", electricityUsed=" + electricityUsed +
                ", carbonSavings=" + carbonSavings +
                '}';
    }
}
