package com.codegym.model;

import com.codegym.controller.ProductManager;

import java.io.Serializable;
import java.util.Comparator;

public class Product implements Comparable<Product>, Comparator<Product>, Serializable {
    private int id;
    private String name;
    private int price;

    public Product() {
    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID="+id+ ", name="+name+", price="+price;
    }

    @Override
    public int compareTo(Product product) {
        return price-product.getPrice();
    }

    @Override
    public int compare(Product product1, Product product2) {
        return product1.getName().compareToIgnoreCase(product2.getName());
    }
}