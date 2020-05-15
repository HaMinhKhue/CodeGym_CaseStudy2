package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookManager<T extends Book> extends ProductManager {
    static boolean flag=true;

    public static final String INVALID_INPUT_TRY_AGAIN = "###invalid input, try again: ";

    public BookManager(String finalFilePath, /*String objectFilePath,*/ String addictionalFilePath, String crawledFilePath) throws IOException {
        setFinalFile(finalFilePath);
//        setObjectFile(objectFilePath);
        setAddictionalFile(addictionalFilePath);
        setCrawledFile(crawledFilePath);
    }

    public void addBookByKeyBoard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter 1 to keep adding book, enter 2 to exit the function: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "2":
                System.out.println();
                return;
            case "1":
                System.out.println("=> ok let's go:");
                break;
            default:
                System.out.println(INVALID_INPUT_TRY_AGAIN);
                addBookByKeyBoard();
        }

        try {
            System.out.print("enter name: ");
            String name = scanner.nextLine();
            System.out.print("enter price: ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.print("enter genre: ");
            String genre = scanner.nextLine();
            System.out.print("enter author: ");
            String author = scanner.nextLine();
            addProduct(new Book(new Random().nextInt(1000000), name, price, genre, author));
        } catch (Exception e) {
            System.out.println("###can't add such a book (invalid input)");
        }
        addBookByKeyBoard();

    }


    public void addBooksByRegulationTxt() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("_write down some books on "+addictionalFile+" for adding them to official list");
        System.out.print("enter 1 to make sure you has finished the writting, enter 2 to exit the function: ");
        String choise = scanner.nextLine();

        switch (choise){
            case "2":
                System.out.println();return;
            case "1":
                System.out.println("=> ok let's go:");break;
            default:
                System.out.println(INVALID_INPUT_TRY_AGAIN);
                addBooksByRegulationTxt();
        }
        regulationTxtTo2dLinkedList(addictionalFile.getPath());
        for (Object o : sub2dList) {
            try {
                Book book = new Book();
                List l = (List) o;
                ListIterator iterator = l.listIterator();
                if (iterator.hasNext())
                    book.setId(Integer.parseInt(iterator.next().toString().strip()));
                if (iterator.hasNext())
                    book.setName(iterator.next().toString().strip());
                if (iterator.hasNext())
                    book.setPrice(Integer.parseInt(iterator.next().toString().strip()));
                if (iterator.hasNext())
                    book.setGenre(iterator.next().toString().strip());
                addProduct(book);
                if (iterator.hasNext())
                    book.setAuthor(iterator.next().toString().strip());
            } catch (Exception e) {
                System.err.println("invalid input form exception");
            }
        }
        System.out.println("=> now all books from addictional file: (" + addictionalFile + ") has been up to official list" + "\n");
    }

    @Override
    public void saveToFinalFile() throws IOException, InterruptedException {
        if (officalList.isEmpty()){
            System.out.println("* nothing to save\n");
            return;
        }
        super.saveToFinalFile();
        System.out.println("_all books saved to final file: " + finalFile + "\n");
    }

    public void crawAndAddBooksFromNyTimes() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter 1 to start crawling book from web, enter 2 to exit the function: ");
        String choise = scanner.nextLine();
        switch (choise) {
            case "2":
                System.out.println();
                return;
            case "1":System.out.println("=> ok, downloading_from_internet:");
                break;
            default:
                System.out.println(INVALID_INPUT_TRY_AGAIN);
                addBookByKeyBoard();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(crawledFile));
        URL bookURL = new URL(ProductManager.BOOK_URL);
        Scanner scanner2 = new Scanner(new InputStreamReader(bookURL.openStream()));
        scanner2.useDelimiter("\\\\Z");
        String content = scanner2.next();
        scanner2.close();
        content = content.replaceAll("\\n", "");


        Matcher matcher = Pattern.compile
                ("role=\"presentation\" alt=\"(.*?) by (.*?)\" class=\"css-35otwa\"")
                .matcher(content);
        bufferedWriter.write("books from nytimes.com:\n\n");
        while (matcher.find()) {
            Book book = new Book();
            book.setName(matcher.group(1));
            book.setAuthor(matcher.group(2));
//            book.setId(creatNewID());

            addProduct(book);
            bufferedWriter.write(book.getName() + "-" + book.getAuthor() + "\n");
        }
        bufferedWriter.close();
        System.out.println("=> now all books from nytimes.com has been up to official list");
        System.out.println("=> check those books in \"crawledbooklist.txt\""+"\n");

    }

    @Override
    public void displayProductList() {
        if (officalList.isEmpty()){
            System.out.println("the current booklist is empty\n");
            return;
        }
        if (flag)
            showDisplayOptions();
        Scanner scanner=new Scanner(System.in);
        String choice =scanner.nextLine();
        switch (choice){
            case "0": return;
            case "1":flag=true;sortByNameInAlphabeticalOrder();
                System.out.println("-->the list has been sorted by alphabeical order of names");break;
            case "2":flag=true;sortByIncreasingPrice();break;
            case "3":flag=true;sortByNameInAlphabeticalOrder();Collections.reverse(officalList);
                System.out.println("-->the list has been sorted by reverse alphabeical order of names");break;
            case "4":flag=true;sortByDecreasingPrice();break;
            default:
                flag=false;System.out.print(INVALID_INPUT_TRY_AGAIN);this.displayProductList();
        }
        System.out.println("the booklist:");
        super.displayProductList();
        this.displayProductList();
    }

    private void showDisplayOptions() {
        System.out.println("enter 1 to display by alphabetical order of names");
        System.out.println("enter 2 to display by increasing order of prices");
        System.out.println("enter 3 to display by reverse alphabeical order of names");
        System.out.println("enter 4 to display by descending order of prices");
        System.out.println("enter 0 to exit the function");
    }
}