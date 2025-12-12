package com.mari;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Librarian> librarians;
    private ArrayList<Client> clients;

    public Library() {
        books = new ArrayList<>();
        librarians = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }
}
