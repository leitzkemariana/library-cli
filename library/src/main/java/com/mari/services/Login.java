package com.mari.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private final ConnectionDB connection = new ConnectionDB();
    private final Connection conn = connection.getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet result;

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
            result = preparedStatement.executeQuery();

            if (result.next()){
                return true;
            } else {
                System.out.println("Invalid email or password.");
                System.out.println();
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

}
