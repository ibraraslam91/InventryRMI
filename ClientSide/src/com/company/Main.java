package com.company;

import java.io.IOException;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        new Main().run();


    }

    public void displayMenu(){

        System.out.println("Select an option by entering the number of the option");
        System.out.println("1. Purchase Details");
        System.out.println("2. Appliance List");
        System.out.println("3. Furniture List");
        System.out.println("4. Kitchenware");
        System.out.println("5. Add a new Purchase");
        System.out.println("6. Exit");
    }

    public ClientPurchaseModel getPurchaseFromUser(){

        System.out.println("Enter Purchase details each part separated by semicolon");
        String inputLine = scanner.nextLine();
        String[] inputArr = inputLine.split(";");
        Purchase newPurchase = new Purchase();
        newPurchase.setDate(inputArr[1]);
        newPurchase.setPrice(Integer.valueOf(inputArr[2]));
        newPurchase.setSupplierName(inputArr[3]);
        newPurchase.setLocation(inputArr[4]);
        ClientPurchaseModel middelPurchase = new ClientPurchaseModel();
        middelPurchase.setCategory(inputArr[0]);
        middelPurchase.setItem(newPurchase);
        return middelPurchase;
    }

    public Appliance getApplianceFromUser(){

        System.out.println("Enter appliance details: ");
        String inputLine = scanner.nextLine();
        String[] inputArr = inputLine.split(";");
        Appliance appliance = new Appliance();
        appliance.setApplianceType(inputArr[0]);
        appliance.setMake(inputArr[1]);
        appliance.setModelNumber(inputArr[2]);
        appliance.setWarranty(Integer.valueOf(inputArr[3]));

        return appliance;

    }

    public Furniture getFurnitureFromUser(){
        System.out.println("Enter furniture details: ");
        String inputLine = scanner.nextLine();
        String[] inputArr = inputLine.split(";");
        Furniture furniture = new Furniture();
        furniture.setFurnitureType(inputArr[0]);
        furniture.setName(inputArr[1]);
        furniture.setNoOfPieces(Integer.valueOf(inputArr[2]));
        furniture.setWarrantyYears(Integer.valueOf(inputArr[3]));

        return furniture;
    }


    public Kitchenware getKitchenwareFromUser(){

        System.out.println("Enter kitchenware details: ");
        String inputLine = scanner.nextLine();
        String[] inputArr = inputLine.split(";");
        Kitchenware kitchenware = new Kitchenware();
        kitchenware.setKitchenwareType(inputArr[0]);
        kitchenware.setMake(inputArr[1]);
        kitchenware.setModelNumber(inputArr[2]);
        kitchenware.setWarranty(Integer.valueOf(inputArr[3]));

        return kitchenware;


    }




    public void run() {

        InventryI inventry =null;
        Socket s = null;
        try{

            int serverPort = 8888;
            s = new Socket("localhost",serverPort);
            Registry registry = LocateRegistry.getRegistry(1099);
            String[] lists = new String[10];
            lists = registry.list();
            inventry = (InventryI) registry.lookup("Inventry");


        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return;
        }
        int choice = 0;
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:

                    ArrayList<Purchase> purchases = null;
                    try {
                        purchases = inventry.displayPurchase();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    for(Purchase purchase : purchases){
                        System.out.println(purchase.toString());
                    }


                    break;
                case 2:

                    ArrayList<Appliance> appliances = null;
                    try {
                        appliances = inventry.displayAppliance();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    for(Appliance appliance : appliances){
                        System.out.println(appliance.toString());
                    }

                    break;
                case 3:
                    ArrayList<Furniture> furnitures = null;
                    try {
                        furnitures = inventry.displayFurniture();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    for(Furniture furniture : furnitures){
                        System.out.println(furniture.toString());
                    }
                    break;
                case 4:
                    ArrayList<Kitchenware> kitchenwares = null;
                    try {
                        kitchenwares = inventry.displayKitchenware();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    for(Kitchenware kitchenware : kitchenwares){
                        System.out.println(kitchenware.toString());
                    }
                    break;
                case 5:

                    ClientPurchaseModel middelPurchaseModel = getPurchaseFromUser();
                    Purchase purchase = middelPurchaseModel.getItem();
                    try {
                        inventry.insertPurchase(purchase.getDate(),purchase.getPrice(),purchase.getSupplierName(),purchase.getLocation());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    if(middelPurchaseModel.getCategory().contains("appliance")){
                        Appliance appliance = getApplianceFromUser();
                        try {
                            inventry.insertAppliance(appliance.getApplianceType(),appliance.getMake(),appliance.getModelNumber(),appliance.getWarranty());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }else if(middelPurchaseModel.getCategory().contains("furniture")){
                        Furniture furniture = getFurnitureFromUser();
                        try {
                            inventry.insertFurniture(furniture.getFurnitureType(),furniture.getName(),furniture.getNoOfPieces(),furniture.getWarrantyYears());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }else if(middelPurchaseModel.getCategory().contains("kitchenware")){
                        Kitchenware kitchenware = getKitchenwareFromUser();
                        try {
                            inventry.insertKitchenware(kitchenware.getKitchenwareType(),kitchenware.getMake(),kitchenware.getModelNumber(),kitchenware.getWarranty());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 6:

                    break;
                default:
                    // should not get here
            }

        }
        while (choice != 6);
    }



}
