package com.techelevator.model.dto;

import com.techelevator.model.Material;

public class MaterialResponse {

    private Material material;
    private double carbonSavings;

    public MaterialResponse(Material material, double carbonSavings) {
        this.material = material;
        this.carbonSavings = carbonSavings;
    }

    public Material getMaterial() {
        return material;
    }

    public double getCarbonSavings() {
        return carbonSavings;
    }
}
