package com.mari;

public class Book {
    public Integer id;
    public String title;
    public String author;
    public String publisher;
    public Integer year;
    private Client client;

    public Book(Integer id, String title, String author, String publisher, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.client = null;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }


    public void showBook() {
        System.out.println("");
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Year: " + year);
    }
}