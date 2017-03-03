package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.io.*;
import java.util.*;

public class DatabaseUtility {
    
    private final  String MYSQL_URL;
    final String DB_URL;
    private final String USER_NAME;
    private final String PASSWORD;
    private Statement statement; 
    private final String dbCreateSQL;
    private final String TABLE_CATEGORY_QRY;
    private final String TABLE_PURCHASE_QRY;
    private final String TABLE_FURNITURE_QRY;
    private final String TABLE_APPLIANCE_QRY;
    private final String TABLE_KITCHENWARE_QRY;
 
    private Connection sqlConnection, dbConnection;
    private DatabaseMetaData dbmd;
    
    private final String DNAME = "Home_Inventory_System";
    private final String TABLE1 = "CATEGORY";
    private final String TABLE2 = "PURCHASE";
    private final String TABLE3 = "FURNITURE";
    private final String TABLE4 = "APPLIANCE";
    private final String TABLE5 = "KITCHENWARE";
    
    public DatabaseUtility (){
     
        MYSQL_URL = "jdbc:mysql://localhost:3306";
        DB_URL = MYSQL_URL +"/Home_Inventory_System";
        //initialise MySql usename and password 
        USER_NAME = "root";
        PASSWORD = "";
        statement = null;
        
        //sql query to create database.
        dbCreateSQL = "CREATE DATABASE Home_Inventory_System";
        
        //sql query to create Tables.
        TABLE_CATEGORY_QRY = "CREATE TABLE CATEGORY" +
                "(CategoryId INTEGER not NULL AUTO_INCREMENT," +
                    "CategoryList VARCHAR(30)," +
                    "PRIMARY KEY (CategoryId))";
                
     
        TABLE_PURCHASE_QRY = "CREATE TABLE PURCHASE" +
                "(purchaseId INTEGER not NULL AUTO_INCREMENT," +
                    "purchaseDate DATE," +
                    "price DECIMAL(12, 2)," + // or FLOAT
                    "supplierName VARCHAR(30)," +
                    "location VARCHAR(30)," +
                    "PRIMARY KEY (PurchaseId))";
     
        TABLE_FURNITURE_QRY = "CREATE TABLE FURNITURE" +
                "(FurnitureTypeId INTEGER not NULL AUTO_INCREMENT," +
                    "FurnitureType VARCHAR(30)," +
                    "setName VARCHAR(30)," +
                    "NoOfPieces INTEGER, " +
                    "warrantyYears INTEGER," +
                    "PRIMARY KEY (FurnitureTypeId))";
                
        
        TABLE_APPLIANCE_QRY = "CREATE TABLE APPLIANCE" +
                "(applianceTypeId INTEGER not NULL AUTO_INCREMENT,"+
                    "applianceType VARCHAR(30)," +
                    "make VARCHAR(30)," +
                    "modelNumber VARCHAR(30)," +
                    "Warranty INTEGER," +
                    "PRIMARY KEY (applianceTypeId))";
        
        TABLE_KITCHENWARE_QRY = "CREATE TABLE KITCHENWARE" +
                "(kitchenWareTypeId INTEGER not NULL AUTO_INCREMENT,"+
                    "kitchenwareType VARCHAR(30)," +
                    "make VARCHAR(30)," +
                    "modelNumber VARCHAR(30)," +
                    "Warranty INTEGER," +
                    "PRIMARY KEY (kitchenWareTypeId))";
    }
    
    public boolean createDBtables() {
        
        boolean dbExists = false, 
                tblCategoryExist = false,
                tblPurchaseExist = false,
                tblFurnitureExist = false,
                tblApplianceExist = false,
                tblKitchenwareExist = false,
                dbCreate = false;
        
        String databaseName ="";
        
        //Register MySql database driver
        try {
             Class.forName("com.mysql.jdbc.Driver");
        }
        
        catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return false;   
        }
        System.out.println("MySQL JDBC Driver Registered!");
        
        //connect to MySql
        try {
            sqlConnection = DriverManager.getConnection(MYSQL_URL, USER_NAME, PASSWORD);
            statement = sqlConnection.createStatement();
            System.out.println("Connected to Mysql"); // to display connection
        }
        
        catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }
        
        //chack whether the databse exists.
        try {
            
            //get the list of databasese
            ResultSet dbData = sqlConnection.getMetaData().getCatalogs();
            
            //interate each catalog in the ResultSet
            while (dbData.next()) {
            // Get the database name, which is at position 1
                databaseName = dbData.getString(1);
                
                //Test print of database names, can be removed
                System.out.printf("$s ", databaseName);
                if (databaseName.equalsIgnoreCase(DNAME))
                    dbExists = true;
            }
            
            // if database doesn't exist create database executing the query.
            if(! dbExists) 
            {
                statement.executeUpdate(dbCreateSQL);
            }
            
            //close the existing connection to connect to MySql
            if (sqlConnection != null)
                sqlConnection.close(); 
        
            //connect to Home_Inventory_System database
            dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            statement = dbConnection.createStatement();
            System.out.println("Connected to Home Inventory System"); // to display connection
            dbmd = dbConnection.getMetaData();
            
            //loop through the list of table if the tables are already created
            ResultSet rs = dbmd.getTables(null, null, "%", null); 
            while (rs.next()) {
                if ((rs.getString(3).equalsIgnoreCase(TABLE1)))
                    tblCategoryExist = true;
                
                if ((rs.getString(3).equalsIgnoreCase(TABLE2)))
                    tblPurchaseExist = true;
                
                if ((rs.getString(3).equalsIgnoreCase(TABLE3)))
                    tblFurnitureExist = true;
                
                if ((rs.getString(3).equalsIgnoreCase(TABLE4)))
                    tblApplianceExist = true;
                
                if ((rs.getString(3).equalsIgnoreCase(TABLE5)))
                    tblKitchenwareExist = true;
            }
            
            //if any of the tables doesn't exist create table executing the query
            if (!tblCategoryExist)
                statement.executeUpdate(TABLE_CATEGORY_QRY);
            
            if (!tblPurchaseExist)
                statement.executeUpdate(TABLE_PURCHASE_QRY);
            
            if (!tblFurnitureExist)
                statement.executeUpdate(TABLE_FURNITURE_QRY);
            
            if (!tblApplianceExist)
                statement.executeUpdate(TABLE_APPLIANCE_QRY);
            
            if (!tblKitchenwareExist)
                statement.executeUpdate(TABLE_KITCHENWARE_QRY);
             
            if (tblKitchenwareExist)
                return true;
            }
            
            catch (SQLException e) {
                   System.out.println("Connection Failed! Check output console");
                   System.out.println("SQLException: " + e.getMessage());
                   System.out.println("SQLState: " + e.getSQLState());
                    e.printStackTrace();
                    return false;
            }
         
        return true;   
        }        
        
        //insert values into the Category tables using user entry
        public void insertCategory (String ct) {
            PreparedStatement addCategory;
            try{
                if (dbConnection == null)
                    dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                    addCategory = dbConnection.prepareStatement("INSERT INTO CATEGORY " +
                                                                "(CategoryList)" +
                                                                "VALUES (?)");
                    addCategory.setString(1, ct);
                    addCategory.executeUpdate();
                    System.out.println("data added");
            }
            
            catch(SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return;
           }
        }
        
        public void insertPurchase (String pdate, int price, String supplier, String location){
            PreparedStatement addPurchase;
            try {
                if (dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                    addPurchase = dbConnection.prepareStatement("INSERT INTO PURCHASE " +
                                                "(purchaseDate, price, supplierName, location)" +
                                                    "VALUES (?, ?, ?, ?)");
                    addPurchase.setString(1, pdate);
                    addPurchase.setInt(2, price);
                    addPurchase.setString(3, supplier);
                    addPurchase.setString(4, location);
                    addPurchase.executeUpdate();
                    System.out.println("Item added");
                    
            }
            
            catch (SQLException e){
             System.out.println("Connection Failed! Check output console");
             System.out.println("SQLException: " + e.getMessage());
             System.out.println("SQLState: " + e.getSQLState());
             
             e.printStackTrace();
             return;
            }
        }
        
        public void insertFurniture (String ftype, String fname, int pieces, int wyears){
            PreparedStatement addFurniture;
            try {
                if(dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                    addFurniture = dbConnection.prepareStatement
                    ("INSERT INTO FURNITURE " +
                      "(FurnitureType, setName, NoOfPieces, warrantyYears)" +
                         "VALUES (?, ?, ?, ?)");
                    
                    addFurniture.setString(1, ftype);
                    addFurniture.setString(2, fname);
                    addFurniture.setInt(3, pieces);
                    addFurniture.setInt(4, wyears);
                    addFurniture.executeUpdate();
                    System.out.println("data added");
            }
            catch(SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return;
            }
        }
        
        public void insertAppliance (String atype, String amake, String anumber, int w) {
            PreparedStatement addAppliance;
            try{
                if(dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                addAppliance = dbConnection.prepareStatement
                        ("INSERT INTO APPLIANCE " +
                          "(applianceType, make, modelNumber, Warranty)" +
                          "VALUES (?, ?, ?, ?)");
                
                addAppliance.setString(1, atype);
                addAppliance.setString(2, amake);
                addAppliance.setString(3, anumber);
                addAppliance.setInt (4, w);
                addAppliance.executeUpdate();
                System.out.println("data added");
            }
            
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return;
            }
        }
        
        public void insertKitchenware (String ktype, String kmake, String knumber, int kw) {
            PreparedStatement addKitchenware;
            try{
                if(dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                addKitchenware = dbConnection.prepareStatement
                        ("INSERT INTO KITCHENWARE " +
                          "(kitchenwareType, make, modelNumber, Warranty)" +
                          "VALUES (?, ?, ?, ?)");
                
                addKitchenware.setString(1, ktype);
                addKitchenware.setString(2, kmake);
                addKitchenware.setString(3, knumber);
                addKitchenware.setInt (4, kw);
                addKitchenware.executeUpdate();
                System.out.println("data added");
            }
            
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return;
            }
        }
        
        public ArrayList<Category> displayCategory() {
            final String EXTRACT_CATEGORY_QRY = "SELECT * FROM CATEGORY";
            ArrayList<Category> categories = new ArrayList<Category>();
            
            try {
                if (dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                    Statement selectionstate = dbConnection.createStatement();
                    ResultSet dbData = selectionstate.executeQuery(EXTRACT_CATEGORY_QRY);
                    ResultSetMetaData metaData = dbData.getMetaData();
                    int numberOfColumns = metaData.getColumnCount();

                    while(dbData.next()) {

                            Category category = new Category();
                            category.setCategoryName(dbData.getString(""));
                            categories.add(category);


                    }

                    return categories;
                    
            }
            
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return null;
            }
        }
        
            public ArrayList<Purchase> displayPurchase() {
            final String EXTRACT_PURCHASE_QRY = "SELECT * FROM PURCHASE";
            ArrayList<Purchase> purchasesList = new ArrayList<Purchase>();
            
            try {
                if (dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                    Statement selectionstate = dbConnection.createStatement();
                    ResultSet dbData = selectionstate.executeQuery(EXTRACT_PURCHASE_QRY);
                    ResultSetMetaData metaData = dbData.getMetaData();
                    int numberOfColumns = metaData.getColumnCount();
                    
                    while(dbData.next()) {
                        Purchase purchase = new Purchase();


                            purchase.setDate(dbData.getString("purchaseDate"));
                            purchase.setSupplierName(dbData.getString("supplierName"));
                            purchase.setLocation(dbData.getString("location"));
                            purchase.setPrice(dbData.getInt("price"));


                        purchasesList.add(purchase);
                    }
                    if(purchasesList.size()>0){
                        System.out.println("Purchase Details sent to Client");
                    }

                    return purchasesList;
                    
            }
            
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return null;
            }
        }
            
            public ArrayList<Furniture> displayFurniture() {
            final String EXTRACT_FURNITURE_QRY = "SELECT * FROM FURNITURE";
            ArrayList<Furniture> furnitures = new ArrayList<Furniture>();
            
            try {
                if (dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                    Statement selectionstate = dbConnection.createStatement();
                    ResultSet dbData = selectionstate.executeQuery(EXTRACT_FURNITURE_QRY);
                    ResultSetMetaData metaData = dbData.getMetaData();
                    int numberOfColumns = metaData.getColumnCount();
                    
                    while(dbData.next()) {

                           Furniture furniture = new Furniture();
                           furniture.setFurnitureType(dbData.getString("FurnitureType"));
                           furniture.setName(dbData.getString("setName"));
                           furniture.setNoOfPieces(dbData.getInt("NoOfPieces"));
                           furniture.setWarrantyYears(dbData.getInt("warrantyYears"));
                           furnitures.add(furniture);

                    }

                    return furnitures;
                    
            }
            
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return null;
            }
        }
            
            public ArrayList<Appliance> displayAppliance() {
            final String EXTRACT_APPLIANCE_QRY = "SELECT * FROM APPLIANCE";
            ArrayList<Appliance> appliances = new ArrayList<Appliance>();
            try {
                if (dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                    Statement selectionstate = dbConnection.createStatement();
                    ResultSet dbData = selectionstate.executeQuery(EXTRACT_APPLIANCE_QRY);
                    ResultSetMetaData metaData = dbData.getMetaData();
                    int numberOfColumns = metaData.getColumnCount();
                    
                    while(dbData.next()) {

                            Appliance appliance = new Appliance();
                            appliance.setApplianceType(dbData.getString("applianceType"));
                            appliance.setMake(dbData.getString("make"));
                            appliance.setModelNumber(dbData.getString("modelNumber"));
                            appliance.setWarranty(dbData.getInt("Warranty"));
                            appliances.add(appliance);



                    }

                    return appliances;
                    
            }
            
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return null;
            }
        }
            
        public ArrayList<Kitchenware> displayKitchenware() {
            final String EXTRACT_KITCHENWARE_QRY = "SELECT * FROM KITCHENWARE";
            ArrayList<Kitchenware> kitchenwares = new ArrayList<Kitchenware>();

            try {
                if (dbConnection == null)
                    dbConnection = DriverManager.getConnection (DB_URL, USER_NAME, PASSWORD);
                    Statement selectionstate = dbConnection.createStatement();
                    ResultSet dbData = selectionstate.executeQuery(EXTRACT_KITCHENWARE_QRY);
                    ResultSetMetaData metaData = dbData.getMetaData();
                    int numberOfColumns = metaData.getColumnCount();
                    

                    while(dbData.next()) {


                            Kitchenware kitchenware = new Kitchenware();
                            kitchenware.setWarranty(dbData.getInt("Warranty"));
                            kitchenware.setModelNumber(dbData.getString("modelNumber"));
                            kitchenware.setMake(dbData.getString("make"));
                            kitchenware.setKitchenwareType(dbData.getString("kitchenwareType"));
                            kitchenwares.add(kitchenware);

                    }

                    return kitchenwares;
                    
            }
            
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                e.printStackTrace();
                return null;
            }
        }
    
    
}
