package com.mari.services;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
     private String url = "jdbc:sqlite:resources/library.db";
     private java.sql.Connection conn = null;

    public java.sql.Connection connect() {
         try{
             if (conn == null) {
                 conn = DriverManager.getConnection(url);
                 //System.out.println("Database connection established");
                 //System.out.println("Using: " + new File("resources/library.db").getAbsolutePath());
             }

         } catch (SQLException e){
             System.out.println(e.getMessage());
         }
         return conn;
     }
}
