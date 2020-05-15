package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Product;

import java.util.Scanner;

public class Searching<T extends Book> {
    static boolean flag=true;
    static boolean priceFlag=true;
    public void searchBookByName(String name, BookManager bM) throws InterruptedException {
        int count = 0;
        System.out.println("_by name \"" + name + "\" we have:");
        for (Object element :
                bM.officalList) {
            String a = StringHandle.removeAccent(((Book) element).getName().toLowerCase().replaceAll(" ", ""));
            String b = StringHandle.removeAccent(name.toLowerCase().replaceAll(" ", ""));
            if (a.matches(".*?" + b + ".*?")) {
                Thread.sleep(250);
                System.out.println(element.toString());
                count++;
            }
        }
        System.out.println("=> " + count + " book(s) found\n");
    }

    public void searchBookByAuthor(String author, BookManager bM) throws InterruptedException {
        int count = 0;
        System.out.println("_by author \"" + author + "\" we have:");
        for (Object element :
                bM.officalList) {
            String a = StringHandle.removeAccent(((Book) element).getAuthor().toLowerCase().replaceAll("\\s", ""));
            String b = StringHandle.removeAccent(author.toLowerCase().replaceAll("\\s", ""));
            if (a.matches(".*" + b + ".*")) {
                Thread.sleep(250);
                System.out.println(element.toString());
                count++;
            }
        }
        System.out.println("=> " + count + " book(s) found\n");
    }

    public void searchBookByPriceRange(int minPrice, int maxPrice, BookManager bM) throws InterruptedException {
        int count = 0;
        System.out.println("_by price \"" + minPrice + " -> " + maxPrice + "\" we have:");
        for (Object element :
                bM.officalList) {
            if (((Book) element).getPrice() >= minPrice && ((Book) element).getPrice() <= maxPrice){
                Thread.sleep(250);
                System.out.println(element.toString());
                count++;
            }
        }
        System.out.println(" [" + count + " book(s) found\n]");
    }

    public void searchBook(BookManager bm) throws InterruptedException {
        if (bm.officalList.isEmpty()){
            System.out.println("===>> the booklist is empty -> nothing to search\n");
            return;
        }
        if (flag)
            showSearchOptions();
        Scanner scanner = new Scanner(System.in);
        String choise = scanner.nextLine();
        switch (choise) {
            case "0":
                return;
            case "1":flag=true;
                System.out.println("<>searching by name is ready<>:");
                Scanner scanner1 = new Scanner(System.in);
                System.out.print("enter name:");
                String name = scanner1.nextLine();
                searchBookByName(name, bm);
                break;
            case "2":flag=true;
                System.out.println("<>searching by author is ready<>:");
                System.out.print("enter author:");
                String author = new Scanner(System.in).nextLine();
                searchBookByAuthor(author, bm);
                break;
            case "3":flag=true;
                do {
                    searchByRangeOfPrice(bm);
                }while (priceFlag);
                break;
            default:flag=false;
                System.out.print("### invalid input, try again: ");

        }
        searchBook(bm);
    }

    private void searchByRangeOfPrice(BookManager bm) {
        try {
            System.out.println("searching by range of price is ready:");
            System.out.print("enter min price:");
            int minPrice = new Scanner(System.in).nextInt();
            System.out.print("enter max price:");
            int maxPrice = new Scanner(System.in).nextInt();
            searchBookByPriceRange(minPrice, maxPrice, bm);
            priceFlag=false;
            return;
        } catch (Exception e) {
            priceFlag=true;
            System.out.println("### invalid input");
            return;
        }
    }

    private void showSearchOptions() {
        System.out.println("<<searching is ready now>>");
        System.out.println("enter 1 to search by name");
        System.out.println("enter 2 to search by author");
        System.out.println("enter 3 to search by range of price");
        System.out.println("enter 0 to exit the function");
    }
}
