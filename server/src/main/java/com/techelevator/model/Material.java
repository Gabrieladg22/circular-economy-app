package com.techelevator.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Material {

    private int materialId;
    @NotNull
    private int businessId;
    @NotNull
    private String materialName;
    @NotNull
    private String condition;
    @NotNull
    private double price;
    @NotNull
    private double quantityKg;
    @NotNull
    private boolean isAvailableForTrading;
    private String createdBy;

    public Material(int materialId, int businessId, String materialName, String condition, double price,
                    double quantityKg, boolean isAvailableForTrading, String createdBy) {
        this.materialId = materialId;
        this.businessId = businessId;
        this.materialName = materialName;
        this.condition = condition;
        this.price = price;
        this.quantityKg = quantityKg;
        this.isAvailableForTrading = isAvailableForTrading;
        this.createdBy = createdBy;
    }

    public Material() {}

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public boolean isAvailableForTrading() {

        return isAvailableForTrading;
    }

    public void setAvailableForTrading(boolean availableForTrading) {
        isAvailableForTrading = availableForTrading;
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", businessId=" + businessId +
                ", materialName='" + materialName + '\'' +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                ", quantityKg=" + quantityKg +
                ", isAvailableForTrading=" + isAvailableForTrading +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}

