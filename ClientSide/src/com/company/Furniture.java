package com.company;

import java.io.Serializable;

/**
 * Created by Aleson on 1/18/2017.
 */
public class Furniture implements Serializable {

    String FurnitureType,Name;
    int NoOfPieces,warrantyYears;


    public Furniture() {
    }

    public Furniture(String furnitureType, String name, int noOfPieces, int warrantyYears) {
        FurnitureType = furnitureType;
        Name = name;
        NoOfPieces = noOfPieces;
        this.warrantyYears = warrantyYears;
    }

    public String getFurnitureType() {
        return FurnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        FurnitureType = furnitureType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNoOfPieces() {
        return NoOfPieces;
    }

    public void setNoOfPieces(int noOfPieces) {
        NoOfPieces = noOfPieces;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    public void setWarrantyYears(int warrantyYears) {
        this.warrantyYears = warrantyYears;
    }


    @Override
    public String toString() {
        return "Furniture{" +
                "FurnitureType='" + FurnitureType + '\'' +
                ", Name='" + Name + '\'' +
                ", NoOfPieces=" + NoOfPieces +
                ", warrantyYears=" + warrantyYears +
                '}';
    }
}
