package com.codegym.test;

import com.codegym.controller.ProductManager;
import com.codegym.model.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductManagerTest {

    @Test
    public void makeNewProductByRegulartionString() {
        String string="11, Strange Stories from a Chinese Studio,850,talk studio strange tales, Bồ Tùng Linh";
        Product expected=new Product(11,"Strange Stories from a Chinese Studio",850);
        Product actual= new ProductManager<>().makeNewProductByRegulartionString(new Product(), string);
        assertEquals(expected,actual);
    }
}