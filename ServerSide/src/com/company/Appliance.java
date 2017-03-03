package com.company;

import java.io.Serializable;

/**
 * Created by Aleson on 1/18/2017.
 */
public class Appliance implements Serializable {

    String applianceType,make,modelNumber;
    int Warranty;


    public Appliance() {
    }

    public Appliance(String applianceType, String make, String modelNumber, int warranty) {
        this.applianceType = applianceType;
        this.make = make;
        this.modelNumber = modelNumber;
        Warranty = warranty;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
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
        return "Appliance{" +
                "applianceType='" + applianceType + '\'' +
                ", make='" + make + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", Warranty=" + Warranty +
                '}';
    }
}
