package com.mari.entities;

import com.mari.services.HandlerDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Librarian extends Person {
    HandlerDB handlerDB = new HandlerDB();
    private Connection conn = handlerDB.getConn();
    private PreparedStatement statement;

    private Library library;

    public Librarian(String name, String email, String password, Library library) {
        super(name, email, password);
        this.library = library;
    }

    public void addBook(Book book) {
        try {
            statement = conn.prepareStatement("INSERT INTO Books (title, author, publisher, year) VALUES (?, ?, ?, ?)");
            statement.setString(1, book.title);
            statement.setString(2, book.author);
            statement.setString(3, book.publisher);
            statement.setInt(4, book.year);
            statement.execute();
            System.out.println("Book added");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeBook(String bookTitle) {
        try {
            statement = conn.prepareStatement("DELETE FROM Books WHERE title = ?");
            statement.setString(1, bookTitle);
            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Book removed");
            } else {
                System.out.println("Book not found");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void showBooks() {
        try {
            statement = conn.prepareStatement("SELECT * FROM Books");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Book book = new Book(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getString("publisher"),
                    result.getInt("year")
                );
                System.out.println(book);

                Integer id = result.getInt("borrowed_by");
                if (!result.wasNull()) {
                    statement = conn.prepareStatement("SELECT * FROM Clients WHERE id = ?");
                    statement.setInt(1, result.getInt("borrowed_by"));
                    ResultSet resultClient = statement.executeQuery();

                    System.out.println("Situation : Unavailable");
                    System.out.println("borrowed by: " + resultClient.getString("name"));

                } else{
                    System.out.println("Situation : Available");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void searchBook(String bookTitle) {
        try {
            statement = conn.prepareStatement("SELECT * FROM Books WHERE title = ?");
            statement.setString(1, bookTitle);
            ResultSet result = statement.executeQuery();

            if (result.next()){
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Title: " + result.getString("title"));
                System.out.println("Author: " + result.getString("author"));
                System.out.println("Publisher: " + result.getString("publisher"));
                System.out.println("Year: " + result.getInt("year"));

                Integer id = result.getInt("borrowed_by");

                if (!result.wasNull()) {
                    statement = conn.prepareStatement("SELECT * FROM Clients WHERE id = ?");
                    statement.setInt(1, result.getInt("borrowed_by"));
                    ResultSet resultClient = statement.executeQuery();

                    System.out.println("Situation : Unavailable");
                    System.out.println("borrowed by: " + resultClient.getString("name"));

                } else {
                    System.out.println("Situation : Available");
                }
            } else {
                System.out.println("Book not found");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void loanBook(Integer bookID, String clientEmail) {
        try {
            statement = conn.prepareStatement("SELECT * FROM Books WHERE id = ?");
            statement.setInt(1, bookID);
            ResultSet resultBook = statement.executeQuery();

            if (resultBook.next()) {
                Integer borrowed = resultBook.getInt("borrowed_by");
                Boolean isAvaible = resultBook.wasNull();

                statement = conn.prepareStatement("SELECT * FROM Clients WHERE email = ?");
                statement.setString(1, clientEmail);
                ResultSet resultClient = statement.executeQuery();

                if (isAvaible){
                    if (resultClient.next()) {
                        statement = conn.prepareStatement("UPDATE Books SET borrowed_by = ? WHERE id = ?");
                        statement.setInt(1, resultClient.getInt("id"));
                        statement.setInt(2, resultBook.getInt("id"));
                        statement.executeUpdate();

                        System.out.println("Book loaned");

                    } else {
                        System.out.println("Client not found");
                    }

                } else {
                    System.out.println("Book is not available");
                }

            } else {
                System.out.println("Book not found");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(String bookTitle) {
        try {
            statement = conn.prepareStatement("SELECT * FROM Books WHERE title = ?");
            statement.setString(1, bookTitle);
            ResultSet result = statement.executeQuery();

            if (result.next()){
                try{
                    statement = conn.prepareStatement("UPDATE Books SET borrowed_by = null WHERE title = ?");
                    statement.setString(1, bookTitle);
                    statement.executeUpdate();
                    System.out.println("Book returned");

                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Book not found");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void addClient(Client client) {
        try{
            statement = conn.prepareStatement("SELECT * FROM Clients WHERE email = ?");
            statement.setString(1, client.getEmail());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                System.out.println("email already registered");
            } else {

                try {
                    statement = conn.prepareStatement("INSERT INTO Clients (name, email, password) VALUES (?, ?, ?)");
                    statement.setString(1, client.getName());
                    statement.setString(2, client.getEmail());
                    statement.setString(3, client.getPassword());
                    statement.execute();
                    System.out.println("Client added");

                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeClient(String clientName) {
        try {
            statement = conn.prepareStatement("SELECT * FROM Clients WHERE name = ?");
            statement.setString(1, clientName);
            ResultSet result = statement.executeQuery();

            try{
                statement = conn.prepareStatement("SELECT * FROM Books WHERE borrowed_by = ?");
                statement.setInt(1, result.getInt("id"));
                ResultSet resultBorrowed = statement.executeQuery();

                if (resultBorrowed.next()){
                    System.out.println("Client still has borrowed books");

                } else{

                    try {
                        statement = conn.prepareStatement("DELETE FROM Clients WHERE name = ?");
                        statement.setString(1, clientName);
                        int rows = statement.executeUpdate();

                        if (rows > 0) {
                            System.out.println("Client removed");
                        } else {
                            System.out.println("Client not found");
                        }

                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }

            } catch (SQLException e){
                System.out.println(e.getMessage());
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void showClients() {

        try {
            statement = conn.prepareStatement("SELECT * FROM Clients");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Client client =  new Client(
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password")
                );

                System.out.println(client);
                Integer id =  result.getInt("id");
                System.out.println("--- Borrowed Books ---");

                try {
                    statement = conn.prepareStatement("SELECT * FROM Books WHERE borrowed_by = ?");
                    statement.setInt(1, result.getInt("id"));
                    ResultSet resultBook = statement.executeQuery();

                    while (resultBook.next()) {
                        System.out.println("Title: " + resultBook.getString("title"));
                    }

                    System.out.println("---------------------- \n");

                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void searchClient(String clientName) {
        try{
            statement = conn.prepareStatement("SELECT * FROM Clients WHERE name = ?");
            statement.setString(1, clientName);
            ResultSet result = statement.executeQuery();

            if (result.getString("name") == null){
                System.out.println("Client not found");

            } else {
                Client client =  new Client(
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password")
                );

                System.out.println(client);

                try {
                    statement = conn.prepareStatement("SELECT * FROM Books WHERE borrowed_by = ?");
                    statement.setInt(1, result.getInt("id"));
                    ResultSet resultBooks = statement.executeQuery();

                    if (resultBooks.next()) {
                        System.out.println("--- Borrowed books ---");
                        System.out.println("Title: " + resultBooks.getString("title"));
                        System.out.println("");
                    }

                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}