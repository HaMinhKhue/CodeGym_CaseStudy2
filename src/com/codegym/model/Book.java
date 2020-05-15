package com.codegym.model;

import java.io.IOException;

public class Book extends Product {
    String genre;
    String author="unknown";
    public Book() {
    }

    public Book(int id, String name, int price, String genre) {
        super(id, name, price);
        this.genre = genre;
    }

    public Book(int id, String name, int price) {
        super(id, name, price);
    }

    public Book(String genre, String author) {
        this.genre = genre;
        this.author = author;
    }

    public Book(int id, String name, int price, String genre, String author) {
        super(id, name, price);
        this.genre = genre;
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public void setGenre(String genre) {

        this.genre = genre;
    }

    @Override
        public String toString() {
            return "book{" + super.toString() + ", genre="+genre+", author= "+author+"}";

    }
}

