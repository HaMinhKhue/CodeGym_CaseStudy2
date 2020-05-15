package com.codegym.test;

import com.codegym.controller.Importer;
import com.codegym.model.Book;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImporterTest {

    @Test
    public void makeNewBookByRegulationString() {
        String string="11, Strange Stories from a Chinese Studio, 850,talk studio strange tales, Bồ Tùng Linh";
        Book expected=new Book(11,"Strange Stories from a Chinese Studio",850,"talk studio strange tales","Bồ Tùng Linh");
        Book actual=new Importer<>().makeNewBookByRegulationString(string);
        assertEquals(expected.toString(),actual.toString());
    }
}