package com.mari.entities;

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

    public String toString() {
        return String.format("%nID: %s %nTitle: %s %nAuthor: %s %nPublisher: %s %nYear: %s",
                id, title, author, publisher, year);
    }
}