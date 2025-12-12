package com.mari.services;

import com.mari.Librarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HandlerDB {
    private ConnectionDB connection = new ConnectionDB();
    private Connection conn = connection.connect();
    private PreparedStatement preparedStatement;

    public Connection getConn() {
        return conn;
    }

    public Boolean Login (Integer option, String email, String password) {
        String query = "";

        if (option == 1) {
            query = "SELECT * FROM Librarians WHERE email = ? AND password = ?";
        } else if (option == 2) {
            query = "SELECT * FROM Clients WHERE email = ? AND password = ?";
        }

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()){
                return true;
            } else {
                System.out.println("Invalid email or password.");
                return false;
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void AddLibrarian(Librarian librarian) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Librarians (name,email,password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, librarian.getName());
            preparedStatement.setString(2, librarian.getEmail());
            preparedStatement.setString(3, librarian.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
