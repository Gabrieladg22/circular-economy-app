
package com.techelevator.model.api;

public class CarbonSavingsApi {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CarbonSavingsApi{" +
                "data=" + data +
                '}';
    }
}
