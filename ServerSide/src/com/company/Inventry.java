package com.company;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Aleson on 1/18/2017.
 */
public class Inventry extends UnicastRemoteObject implements InventryI {

    DatabaseUtility dbUtility = new DatabaseUtility();

    protected Inventry() throws RemoteException {
    }


    @Override
    public synchronized void insertPurchase(String pdate, int price, String supplier, String location) {
        System.out.println("Add item request received from Client");
        dbUtility.insertPurchase(pdate,price,supplier,location);

    }

    @Override
    public synchronized void insertFurniture(String ftype, String fname, int pieces, int wyears) {

        dbUtility.insertFurniture(ftype,fname,pieces,wyears);
    }

    @Override
    public synchronized void insertAppliance(String atype, String amake, String anumber, int w) {

        dbUtility.insertAppliance(atype,amake,anumber,w);

    }

    @Override
    public synchronized void insertKitchenware(String ktype, String kmake, String knumber, int kw) {
        dbUtility.insertKitchenware(ktype,kmake,knumber,kw);
    }

    @Override
    public ArrayList<Category> displayCategory() {

        return dbUtility.displayCategory();
    }

    @Override
    public ArrayList<Purchase> displayPurchase() {
        System.out.println("Purchase Details request received from Client");
        return dbUtility.displayPurchase();
    }

    @Override
    public ArrayList<Furniture> displayFurniture() {
        System.out.println("Furniture Details request received from Client");
        return dbUtility.displayFurniture();
    }

    @Override
    public ArrayList<Appliance> displayAppliance() {
        System.out.println("Appliance Details request received from Client");
        return dbUtility.displayAppliance();
    }

    @Override
    public ArrayList<Kitchenware> displayKitchenware() {
        System.out.println("Kitchenware Details request received from Client");
        return dbUtility.displayKitchenware();
    }
}
