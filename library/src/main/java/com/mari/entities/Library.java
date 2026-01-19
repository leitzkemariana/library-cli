package com.mari.entities;

import com.mari.services.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Library {
    private final ConnectionDB connection = new ConnectionDB();
    private final Connection conn = connection.getConnection();
    private PreparedStatement statement;

    public void addLibrarian(Librarian librarian) {
        try {
            statement = conn.prepareStatement("INSERT INTO Librarians (name,email,password) VALUES (?, ?, ?)");
            statement.setString(1, librarian.getName());
            statement.setString(2, librarian.getEmail());
            statement.setString(3, librarian.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
