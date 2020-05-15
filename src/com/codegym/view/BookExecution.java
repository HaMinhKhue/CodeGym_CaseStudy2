package com.codegym.view;

import com.codegym.controller.*;
import com.codegym.model.Book;

import java.io.IOException;
import java.util.Scanner;

public class BookExecution{
    static private boolean flag=true;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        BookManager<Book> bM=new BookManager<>(BookManager.FINAL_BOOK_LIST_FILE_PATH,/*BookManager.BOOK_OBJECTS_FILE_PATH,*/BookManager.ADDICTIONAL_BOOK_LIST_FILE_PATH,BookManager.CRAWEDBOOKLIST_FILE_PATH);
        BookExecution.controllBookListGenerally(bM);

    }
    public static void controllBookListGenerally(BookManager bM) throws IOException, InterruptedException {
        if (flag)
            showMainMenu();
        Scanner scanner=new Scanner(System.in);
        String choice=scanner.nextLine();
        switch (choice){
            case "0":return;
            case "1":flag=true;
                new Importer<>().addBookByRegulationString(bM);break;
            case "2":flag=true;bM.addBooksByRegulationTxt();break;
            case "3":flag=true;bM.crawAndAddBooksFromNyTimes();break;
            case "4":flag=true;new Searching<>().searchBook(bM);break;
            case "5":flag=true;new Remover<>().removeBook(bM);break;
            case "6":flag=true;bM.displayProductList();break;
            case "7":flag=true;bM.saveToFinalFile();break;
            case "8":flag=true;new Editor<>().editBookList(bM);break;
            default:
                System.out.print("###invalid input, try again: ");flag=false;
                controllBookListGenerally(bM);

        }
        controllBookListGenerally(bM);
    }

    private static void showMainMenu() {
        System.out.println("HERE IS MAIN MENU");
        System.out.println("enter 1 to add books by typing directly");
        System.out.println("enter 2 to add books from txt file \"addictionalbooklist\"");
        System.out.println("enter 3 to update books from nytimes.com");
        System.out.println("enter 4 to searching books");
        System.out.println("enter 5 to remove books");
        System.out.println("enter 6 to display the booklist (in an optinal sorting)");
        System.out.println("enter 7 to save the booklist");
        System.out.println("enter 8 to edit books");
        System.out.println("enter 0 to quit the program");
    }

}