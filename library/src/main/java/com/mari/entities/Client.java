package com.mari.entities;

import com.mari.services.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client extends Person {
    private final ConnectionDB connection = new ConnectionDB();
    private final Connection conn = connection.getConnection();
    private PreparedStatement statement;
    private ResultSet result;

    public Client(String name, String email, String password) {
        super(name, email, password);
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public ResultSet getResult() {
        return result;
    }

    public void showBooks(String clientEmail) {
        result = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM Clients WHERE email = ?");
            statement.setString(1, clientEmail);
            result = statement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            statement = conn.prepareStatement("SELECT * FROM Books WHERE borrowed_by = (?)");
            statement.setInt(1, result.getInt("id"));
            ResultSet resultBook = statement.executeQuery();

            System.out.println();
            System.out.println("Borrowed Books: ");
            while (resultBook.next()) {
                System.out.println("Title: " + resultBook.getString("title"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchBook(String bookTitle) {
        try {
            statement = conn.prepareStatement("SELECT * FROM Books WHERE title = ?");
            statement.setString(1, bookTitle);
            result = statement.executeQuery();

            if (result.next()) {
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Title: " + result.getString("title"));
                System.out.println("Author: " + result.getString("author"));
                System.out.println("Publisher: " + result.getString("publisher"));
                System.out.println("Year: " + result.getInt("year"));

                Integer id = result.getInt("borrowed_by");

                if (!result.wasNull()) {
                    System.out.println("Situation : Unavailable");

                } else {
                    System.out.println("Situation : Available");
                }
            } else {
                System.out.println("Book not found");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
