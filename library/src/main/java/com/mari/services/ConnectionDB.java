package com.mari.services;

import java.sql.*;

public class ConnectionDB {
     private static Connection conn = null;

     public static Connection getConnection(){
         try {
             if (conn == null) {
                 String url = "jdbc:sqlite:resources/library.db";
                 conn = DriverManager.getConnection(url);
             }
         } catch (SQLException ex) {
             throw  new RuntimeException(ex.getMessage());
         }
        return conn;
     }

     public static void closeConnection(){
         if(conn!=null){
             try {
                 conn.close();
             } catch (SQLException ex) {
                 throw  new RuntimeException(ex.getMessage());
             }
         }
     }

     public static void closeStatement(Statement stmt){
         if(stmt!=null){
             try {
                 stmt.close();
             } catch (SQLException ex) {
                 throw  new RuntimeException(ex.getMessage());
             }
         }
     }

     public static void closeResultSet(ResultSet rs){
         if(rs!=null){
             try {
                 rs.close();
             } catch (SQLException ex) {
                 throw  new RuntimeException(ex.getMessage());
             }
         }
     }
}
