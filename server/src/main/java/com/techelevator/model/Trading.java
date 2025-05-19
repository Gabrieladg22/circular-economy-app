package com.techelevator.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class Trading {

    private int tradeId;
    @NotNull
    private int offeredMaterialId;
    @NotNull
    private int requestedMaterialId;
    @NotNull
    private int offeredByBusinessId;
    @NotNull
    private int requestedToBusinessId;
    @NotNull
    private String tradeStatus;
    @NotNull
    private String tradeDate;
    private String createdBy;


    public Trading(){}

    public Trading(int tradeId, int offeredMaterialId, int requestedMaterialId, int offeredByBusinessId,
                   int requestedToBusinessId, String tradeStatus, String tradeDate, String createdBy) {
        this.tradeId = tradeId;
        this.offeredMaterialId = offeredMaterialId;
        this.requestedMaterialId = requestedMaterialId;
        this.offeredByBusinessId = offeredByBusinessId;
        this.requestedToBusinessId = requestedToBusinessId;
        this.tradeStatus = tradeStatus;
        this.tradeDate = tradeDate;
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getOfferedMaterialId() {
        return offeredMaterialId;
    }

    public void setOfferedMaterialId(int offeredMaterialId) {
        this.offeredMaterialId = offeredMaterialId;
    }

    public int getRequestedMaterialId() {
        return requestedMaterialId;
    }

    public void setRequestedMaterialId(int requestedMaterialId) {
        this.requestedMaterialId = requestedMaterialId;
    }

    public int getOfferedByBusinessId() {
        return offeredByBusinessId;
    }

    public void setOfferedByBusinessId(int offeredByBusinessId) {
        this.offeredByBusinessId = offeredByBusinessId;
    }

    public int getRequestedToBusinessId() {
        return requestedToBusinessId;
    }

    public void setRequestedToBusinessId(int requestedToBusinessId) {
        this.requestedToBusinessId = requestedToBusinessId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }


    @Override
    public String toString() {
        return "Trading{" +
                "tradeId=" + tradeId +
                ", offeredMaterialId=" + offeredMaterialId +
                ", requestedMaterialId=" + requestedMaterialId +
                ", offeredByBusinessId=" + offeredByBusinessId +
                ", requestedToBusinessId=" + requestedToBusinessId +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", tradeDate=" + tradeDate +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
