package com.company;

import java.io.Serializable;

/**
 * Created by Aleson on 1/18/2017.
 */
public class Purchase implements Serializable {

    String date, supplierName, location;
    int price;

    public Purchase() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "date='" + date + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                '}';
    }
}
