package com.mari.entities;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Integer year;

    public Book(String title, String author, String publisher, Integer year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    public Book(Integer id, String title, String author, String publisher, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String toString() {
        return String.format("%nID: %s %nTitle: %s %nAuthor: %s %nPublisher: %s %nYear: %s",
                id, title, author, publisher, year);
    }
}
