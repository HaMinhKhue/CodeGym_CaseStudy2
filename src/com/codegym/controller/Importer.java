package com.codegym.controller;

import com.codegym.model.Book;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Importer<T extends Book> {
    public void addBookByRegulationString(BookManager bm) throws InterruptedException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("enter 1 to continue, 2 to exit the function: ");
        String choice=scanner.nextLine();
        switch (choice){
            case "2":return;
            case "1":break;
            default:System.out.println("###invalid input, try again:");
                addBookByRegulationString(bm);
        }
        System.out.println("type as the pattern: \"(int)ID, (String)name, (int)price, (String)genre, (String)author\" [coma separated] to adding new book:");
        System.out.print("type:");
        String string=scanner.nextLine();
        LinkedList<String> atributeList=new LinkedList<String>(Arrays.asList(string.split(",", 0)));
        try {
            Book book=new Book();
            ListIterator iterator = atributeList.listIterator();
            if (iterator.hasNext())
                book.setId(Integer.parseInt(iterator.next().toString().strip()));
            if (iterator.hasNext())
                book.setName(iterator.next().toString().strip());
            if (iterator.hasNext())
                book.setPrice(Integer.parseInt(iterator.next().toString().strip()));
            if (iterator.hasNext())
                book.setGenre(iterator.next().toString().strip());
            if (iterator.hasNext())
                book.setAuthor(iterator.next().toString().strip());
            bm.addProduct(book);
        }catch (Exception e) {
            System.out.println("### invalid input form exception!");
        }
        addBookByRegulationString(bm);
//        Book book=makeNewBookByRegulationString(string);
//        if (book!=null)
//            bm.addProduct(book);
//        addBookByRegulationString(bm);

    }
    public Book makeNewBookByRegulationString(String string){
        LinkedList<String> atributeList=new LinkedList<String>(Arrays.asList(string.split(",", 0)));

            Book book=new Book();
        try {
            ListIterator iterator = atributeList.listIterator();
            if (iterator.hasNext())
                book.setId(Integer.parseInt(iterator.next().toString().strip()));
            if (iterator.hasNext())
                book.setName(iterator.next().toString().strip());
            if (iterator.hasNext())
                book.setPrice(Integer.parseInt(iterator.next().toString().strip()));
            if (iterator.hasNext())
                book.setGenre(iterator.next().toString().strip());
            if (iterator.hasNext())
                book.setAuthor(iterator.next().toString().strip());
        }catch (Exception e) {
            System.out.println("### invalid input form exception!");
            return null;
        }
        return book;
    }

    public static void main(String[] args) {
        System.out.println(
                new Importer<>().makeNewBookByRegulationString("11, Strange Stories from a Chinese Studio, 850,talk studio strange tales, Bồ Tùng Linh")
        );
    }
}
