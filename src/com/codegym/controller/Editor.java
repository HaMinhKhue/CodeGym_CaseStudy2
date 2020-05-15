package com.codegym.controller;

import com.codegym.model.Book;

import java.util.Scanner;

import static com.codegym.controller.BookManager.INVALID_INPUT_TRY_AGAIN;

public class Editor <T extends Book>{
    public Book getBookByID(int id,BookManager bm){
        for (Object book:bm.officalList){
            if (((Book) book).getId()==id){
                return (Book) book;
            }
        }
        return null;
    }
    public void editBookList(BookManager bm){
        if (bm.officalList.isEmpty()){
            System.out.println("the booklist is empty -> nothing to edit\n");
            return;
        }
        Scanner scanner0=new Scanner(System.in);
        System.out.print("enter \"y\" to continue editting, other to skip: ");
        String choice=scanner0.nextLine();
        switch (choice.toLowerCase()){
            case "y":break;
            default:return;
        }
        Scanner scanner=new Scanner(System.in);
        System.out.print("enter the ID of book needed to edit: ");
        int id=scanner.nextInt();
        Book book=getBookByID(id,bm);
        if (book==null){
            System.out.println(">> no book by ID= "+id+" try other ID");
            editBookList(bm);
        }
        System.out.println("==>ok, you're about edit: "+book.toString());
        editIDOfBook(book);
        editNameOfBook(book);
        editPriceOfBook(book);
        editGenreOfBook(book);
        editAuthorOfBook(book);
        editBookList(bm);
    }
    public void editIDOfBook(Book book){
        Scanner scanner=new Scanner(System.in);
        System.out.print("edit ID? enter \"y\" to continue, other to skip: ");
        String choice=scanner.nextLine();
        switch (choice.toLowerCase()){
            case "y":
                System.out.print("enter new ID: ");
                try {
                    int oldID=book.getId();
                    book.setId(new Scanner(System.in).nextInt());
                    System.out.println("name change: "+oldID+" -> "+book.getId());return;
                }catch (Exception e){
                    System.out.println("### invalid ID input, must be integer");
//                    System.out.println(INVALID_INPUT_TRY_AGAIN);
                    editIDOfBook(book);
                }

            default:return;
        }
    }
    public void editNameOfBook(Book book){
        Scanner scanner=new Scanner(System.in);
        System.out.print("edit name? enter \"y\" to continue, other to skip: ");
        String choice=scanner.nextLine();
        switch (choice.toLowerCase()){
            case "y":
                System.out.print("enter new ID: ");
                try {
                    String oldName=book.getName();
                    book.setName(new Scanner(System.in).nextLine());
                    System.out.println("name change: "+oldName+" -> "+book.getName());return;
                }catch (Exception e){
                    System.out.println("### invalid ID input, must be integer");
                    editIDOfBook(book);
                }

            default:return;
        }
    }
    public void editPriceOfBook(Book book){
        Scanner scanner=new Scanner(System.in);
        System.out.print("edit price? enter \"y\" to continue, other to skip: ");
        String choice=scanner.nextLine();
        switch (choice.toLowerCase()){
            case "y":
                System.out.print("enter new ID: ");
                try {
                    int oldPrice=book.getPrice();
                    book.setPrice(new Scanner(System.in).nextInt());
                    System.out.println("price change: "+oldPrice+" -> "+book.getPrice());return;
                }catch (Exception e){
                    System.out.println("### invalid price input, must be integer");
                    editIDOfBook(book);
                }

            default:return;
        }
    }
    public void editGenreOfBook(Book book){
        Scanner scanner=new Scanner(System.in);
        System.out.print("edit genre? enter \"y\" to continue, other to skip: ");
        String choice=scanner.nextLine();
        switch (choice.toLowerCase()){
            case "y":
                System.out.print("enter new genre: ");
                try {
                    String oldGenre=book.getGenre();
                    book.setId(new Scanner(System.in).nextInt());
                    System.out.println("price changed: "+oldGenre+" -> "+book.getGenre());return;
                }catch (Exception e){
                    System.out.println("### invalid ID input, must be integer");
                    editIDOfBook(book);
                }

            default:return;
        }
    }
    public void editAuthorOfBook(Book book){
        Scanner scanner=new Scanner(System.in);
        System.out.print("edit author? enter \"y\" to continue, other to skip: ");
        String choice=scanner.nextLine();
        switch (choice.toLowerCase()){
            case "y":
                System.out.print("enter new author: ");
                try {
                    String oldAuthor=book.getAuthor();
                    book.setId(new Scanner(System.in).nextInt());
                    System.out.println("author changed: "+oldAuthor+" -> "+book.getAuthor());return;
                }catch (Exception e){
                    System.out.println("### invalid ID input, must be integer");
                    editIDOfBook(book);
                }

            default:return;
        }
    }
}
