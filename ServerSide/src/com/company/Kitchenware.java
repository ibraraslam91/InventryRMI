package com.company;

import java.io.Serializable;

/**
 * Created by Aleson on 1/18/2017.
 */
public class Kitchenware implements Serializable {

    String kitchenwareType,make,modelNumber;
    int Warranty;




    public String getKitchenwareType() {
        return kitchenwareType;
    }

    public void setKitchenwareType(String kitchenwareType) {
        this.kitchenwareType = kitchenwareType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public int getWarranty() {
        return Warranty;
    }

    public void setWarranty(int warranty) {
        Warranty = warranty;
    }

    @Override
    public String toString() {
        return "Kitchenware{" +
                "kitchenwareType='" + kitchenwareType + '\'' +
                ", make='" + make + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", Warranty=" + Warranty +
                '}';
    }
}
