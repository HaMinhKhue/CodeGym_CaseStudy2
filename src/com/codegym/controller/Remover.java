package com.codegym.controller;

import com.codegym.model.Book;

import java.util.Scanner;

public class Remover<T extends Book> {
    public void removeBookByName(String name, BookManager bm) {
        int count = 0;
        for (Object element :
                bm.officalList) {
            if (((Book) element).getName().equalsIgnoreCase(name)) {
                if (bm.officalList.remove(element))
                    count++;
            }
        }
        System.out.println("->" + count + " product(s) deleted by name \"" + name + "\"\n");
    }

    public void removeBookByAuthor(String author, BookManager bm) {
        int count = 0;
        for (Object element :
                bm.officalList) {
            if (((Book) element).getAuthor().equalsIgnoreCase(author)) {
                if (bm.officalList.remove(element))
                    count++;
            }
        }
        System.out.println("_" + count + " product(s) deleted by name \"" + author + "\"\n");
    }

    public void removeBookByPosition(int index, BookManager bm) {
        if (!bm.officalList.remove(bm.officalList.get(index))) {
            System.out.println("no product by position=" + index + " to remove\n");
        } else {
            System.out.println("the product by position=" + index + " has been remove form list\n");
        }
    }

    public void removeByID(int ID, BookManager bm) {
        if (!bm.officalList.remove(bm.getProductByID(ID))) {
            System.err.println("no product by ID=" + ID + " to remove\n");
        } else {
            System.out.println("the product by ID=" + ID + " has been remove form list\n");
        }
    }

    public void removeBook(BookManager bm) {
        if (bm.officalList.isEmpty()){
            System.out.println("===>> the booklist is empty -> nothing to remove\n");
            return;
        }
        System.out.println("remover is ready to start");
        System.out.println("enter 1 to remove by name");
        System.out.println("enter 2 to remove by author");
        System.out.println("enter 3 to remove by position");
        System.out.println("enter 4 to remove by ID");
        System.out.println("enter 5 to exit the function");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        try {
            switch (choice) {
                case "5":
                    return;
                case "1":
                    String name = new Scanner(System.in).nextLine();
                    removeBookByName(name, bm);
                    break;
                case "2":
                    String author = new Scanner(System.in).nextLine();
                    removeBookByAuthor(author, bm);
                    break;
                case "3":
                    System.out.println("ok, enter 1 or more positions, space-separated, end by alphabet");
                    int position=0;
                    do {
                        position = scanner.nextInt();
                        removeBookByPosition((position), bm);
                    }while (scanner.hasNextInt());

                    break;
                case "4":
                    int ID = 0;
                    do {
                        ID = scanner.nextInt();
                        removeBookByPosition((ID), bm);
                    }while (scanner.hasNextInt());
                    break;
                default:
                    System.out.println("### invalid input, try again:");
            }
        } catch (Exception e) {
            System.out.println("### invalid input");
        }
        removeBook(bm);
    }
}