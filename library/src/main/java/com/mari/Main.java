package com.mari;

import com.mari.entities.Book;
import com.mari.entities.Client;
import com.mari.entities.Librarian;
import com.mari.entities.Library;
import com.mari.services.ConnectionDB;
import com.mari.services.Exceptions;
import com.mari.services.Login;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Librarian", "librarian@gmail", "123", library);
        //library.addLibrarian(librarian);

        Login login = new Login();

        while (true) {
            Scanner input = new Scanner(System.in);
            Integer option = 0;
            System.out.println("---- Library Menu ----");
            System.out.println("1. Access librarian menu");
            System.out.println("2. Access client menu");
            System.out.println("3. Exit");

            option = Exceptions.number("Enter your choice: ");

            if (option == 1) {
                System.out.println();
                String libEmail = Exceptions.answer("Email: ");
                String password = Exceptions.answer("Password: ");

                if (login.Login(1, libEmail, password) == true) {
                    while (true) {
                        System.out.println();
                        System.out.println("----Librarian Menu----");
                        System.out.println("1. Add book");
                        System.out.println("2. Delete book");
                        System.out.println("3. Show books");
                        System.out.println("4. Search book");
                        System.out.println("5. Loan book");
                        System.out.println("6. Return book");
                        System.out.println("7. Add client");
                        System.out.println("8. Delete client");
                        System.out.println("9. Show clients");
                        System.out.println("10. Search client");
                        System.out.println("11. Exit");

                        Integer opLibrarian = Exceptions.number("Enter your choice: ");

                        if (opLibrarian == 1) {
                            System.out.println();
                            String bookTitle = Exceptions.answer("Enter book title: ");
                            String bookAuthor =  Exceptions.answer("Enter book author: ");
                            String bookPublisher = Exceptions.answer("Enter book publisher: ");
                            Integer bookYear = Exceptions.number("Enter year: ");

                            Book book = new Book(bookTitle, bookAuthor, bookPublisher, bookYear);
                            librarian.addBook(book);

                        } else if (opLibrarian == 2) {
                            System.out.println();
                            String bookTitle = Exceptions.answer("Enter book title: ");
                            librarian.removeBook(bookTitle);

                        } else if (opLibrarian == 3) {
                            librarian.showBooks();

                        } else if (opLibrarian == 4) {
                            System.out.println();
                            String bookTitle = Exceptions.answer("Enter book title: ");
                            librarian.searchBook(bookTitle);

                        } else if (opLibrarian == 5) {
                            System.out.println();
                            Integer bookId = Exceptions.number("Enter book ID: ");
                            String clientEmail = Exceptions.answer("Enter client's email: ");

                            librarian.loanBook(bookId, clientEmail);

                        } else if (opLibrarian == 6) {
                            System.out.println();
                            String bookTitle = Exceptions.answer("Enter book title: ");
                            librarian.returnBook(bookTitle);

                        } else if (opLibrarian == 7) {
                            System.out.println();
                            String clientName =  Exceptions.answer("Enter client's name: ");
                            String clientEmail = Exceptions.answer("Enter client's email: ");
                            String clientPassword = Exceptions.answer("Enter client's password: ");

                            Client client = new Client(clientName, clientEmail, clientPassword);
                            librarian.addClient(client);

                        } else if (opLibrarian == 8) {
                            System.out.println();
                            String clientEmail = Exceptions.answer("Enter client's email: ");
                            librarian.removeClient(clientEmail);

                        } else if (opLibrarian == 9) {
                            librarian.showClients();

                        } else if (opLibrarian == 10) {
                            System.out.println();
                            String clientName = Exceptions.answer("Enter client's name: ");
                            librarian.searchClient(clientName);

                        } else if (opLibrarian == 11) {
                            System.out.println();
                            ConnectionDB.closeStatement(librarian.getPreparedStatement());
                            ConnectionDB.closeResultSet(librarian.getResult());
                            break;
                        }
                    }
                }
            }

            if (option == 2) {
                System.out.println();
                String email = Exceptions.answer("Email: ");
                String password = Exceptions.answer("Password: ");

                if (login.Login(2, email, password)) {
                    Client client = new Client(null, email, password);

                    while (true) {
                        System.out.println();
                        System.out.println("----Client Menu----");
                        System.out.println("1. Show borrowed books");
                        System.out.println("2. Search book");
                        System.out.println("3. Exit");

                        Integer opClient = Exceptions.number("Enter your choice: ");

                        if (opClient == 1) {
                            client.showBooks(email);

                        } else if (opClient == 2) {
                            System.out.println();
                            String bookTitle = Exceptions.answer("Enter book title: ");
                            client.searchBook(bookTitle);

                        } else if (opClient == 3) {
                            System.out.println();
                            ConnectionDB.closeStatement(client.getStatement());
                            ConnectionDB.closeResultSet(client.getResult());
                            break;
                        }
                    }

                }
            }

            if (option == 3) {
                Connection conn = ConnectionDB.getConnection();
                ConnectionDB.closeConnection();
                break;
            }
        }
    }
}
