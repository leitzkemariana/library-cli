package com.mari.entities;

import com.mari.services.HandlerDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Library {
    HandlerDB handlerDB = new HandlerDB();
    private Connection conn = handlerDB.getConn();
    private PreparedStatement preparedStatement;

    public void addLibrarian(Librarian librarian) {
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
