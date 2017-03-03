package com.company;

import java.rmi.Remote;
import java.util.ArrayList;
import java.rmi.RemoteException;

/**
 * Created by Aleson on 1/18/2017.
 */
public interface InventryI extends Remote {

    public void insertPurchase (String pdate, int price, String supplier, String location) throws RemoteException;
    public void insertFurniture (String ftype, String fname, int pieces, int wyears) throws RemoteException;
    public void insertAppliance (String atype, String amake, String anumber, int w) throws RemoteException;
    public void insertKitchenware (String ktype, String kmake, String knumber, int kw) throws RemoteException;


    public ArrayList<Category> displayCategory() throws RemoteException;
    public ArrayList<Purchase> displayPurchase() throws RemoteException;
    public ArrayList<Furniture> displayFurniture() throws RemoteException;
    public ArrayList<Appliance> displayAppliance() throws RemoteException;
    public ArrayList<Kitchenware> displayKitchenware() throws RemoteException;


}
