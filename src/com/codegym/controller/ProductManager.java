package com.codegym.controller;

import com.codegym.model.Product;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ProductManager<T extends Product> {
    public static final String FILE_PATH1="productlist1.txt";
    public static final String ADDICTIONAL_BOOK_LIST_FILE_PATH ="addictionalbooklist.txt";
    public static final String OFFICAL_LAPTOP_LIST_FILE_PATH="majorlaptoplist.txt";
    public static final String BOOK_OBJECTS_FILE_PATH = "bookobjects.txt";
    public static final String FINAL_BOOK_LIST_FILE_PATH="finalbooklist.txt";
    public static final String BOOK_URL="https://www.nytimes.com/books/best-sellers/";
    public static final String CRAWEDBOOKLIST_FILE_PATH="crawledbooklist.txt";
    LinkedList<String> subLinkedList =new LinkedList<>();
    LinkedList<Object> sub2dList =new LinkedList<>();
    LinkedList<T> officalList = new LinkedList<>();
    File objectFile,finalFile,addictionalFile,crawledFile;
    public void setCrawledFile(String path) throws IOException {
        crawledFile=new File(path);
        if (!crawledFile.exists())
            crawledFile.createNewFile();
    }
    public void setObjectFile(String path) throws IOException {
        objectFile = new File(path);
        if (!objectFile.exists())
            objectFile.createNewFile();
    }
    public void setFinalFile(String path) throws IOException {
        finalFile=new File(path);
        if (!finalFile.exists())
            finalFile.createNewFile();
    }
    public void setAddictionalFile(String path) throws IOException {
        addictionalFile=new File(path);
        if (!addictionalFile.exists())
            addictionalFile.createNewFile();
    }

    public void txtFileToStringArrayListOfLines(String filepath) throws IOException {
        subLinkedList.clear();
        File file=new File(filepath);
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String s="";
        while ((s=bufferedReader.readLine())!=null){
            subLinkedList.add(s);
        }
    }
    public void regulationTxtTo2dLinkedList(String filepath) throws IOException{
        subLinkedList.clear();
        File file=new File(filepath);
        BufferedReader bufferedReader =new BufferedReader(new FileReader(file));
        String s="";
        while ((s=bufferedReader.readLine())!=null){
            sub2dList.add(Arrays.asList(s.split(",", 0)));
        }
    }
    public void reMakeProductByRegulartionString(T product, String string){//ok
        String[] stringarray =string.split(",",0);
        product.setId(Integer.parseInt(stringarray[0]));
        product.setName(stringarray[1]);
        product.setPrice(Integer.parseInt(stringarray[2]));
    }
    public T makeNewProductByRegulartionString(T product, String string){//ok
        String[] stringarray =string.split(",");
        T subproduct= (T) new Product();
        subproduct.setId(Integer.parseInt(stringarray[0]));
        subproduct.setName(stringarray[1]);
        subproduct.setPrice(Integer.parseInt(stringarray[2]));
        return (T) subproduct;
    }

    public void addProduct(T product) throws InterruptedException {

        for (T t : officalList) {
            if (t.getName().equals(product.getName()))
                return;
            if (t.getId() == product.getId()) {
                product.setId(creatNewID());
//                System.err.printf("can't add %s (ID exited)\n", product.toString());
            }
        }
        officalList.add(product);
        Thread.sleep(250);
        System.out.println(product.toString() + " has been added");
    }
    public void addProductByRegulartionString(T product,String string) throws InterruptedException {//ok
        addProduct((T) makeNewProductByRegulartionString(product,string));
    }
    public void addProductByRegulartionTxtFile(T product, String filepath) throws IOException, InterruptedException {
        txtFileToStringArrayListOfLines(filepath);
        for (String e : subLinkedList) {
            System.out.println("line: "+e);
            System.out.println("product parameter: "+product);
            addProductByRegulartionString(product,e);
        }
    }
    public T getProductByID(int ID){
        for (T product :
                officalList) {
            if (product.getId() == ID)
                return product;
            }
        return null;
    }
    public int creatNewID(){
        int ID =new Random().nextInt(1000000);
        for (T product :
                officalList) {
            if (product.getId() == ID)
                return creatNewID();
        }
        return ID;
    }

    public void renameByID(int ID, String newname) {
        try {
            getProductByID(ID).setName(newname);
            System.out.printf("product by ID=%d had its name changed: \"%s\" -> \"%s\"\n",ID,
                    getProductByID(ID).getName(), newname);
        }catch (Exception e){
            System.err.println("no product by ID="+ID+" to rename");
        }
    }

    public void removeByID(int ID) {
        if(!officalList.remove(getProductByID(ID))){
            System.err.println("no product by ID="+ID+" to remove\n");
        }else {
            System.out.println("the product by ID="+ID+" has been remove form list\n");
        }
    }
    public void searchProductByName(String name){
        int count=0;
        System.out.println("_by name \""+name+"\" we have:");
        for (T element :
                officalList) {
            String a=StringHandle.removeAccent(element.getName().toLowerCase());
            String b=StringHandle.removeAccent(name.toLowerCase());
            if (a.matches(".*"+b+".*")){
                System.out.println(element.toString()); count++;
            }
        }
        System.out.println("=> )"+count+ " product(s) found\n");
    }
    public void removeProductByName(String name){
        int count=0;
        for (T element :
                officalList) {
            if (element.getName().equalsIgnoreCase(name)){
                if (officalList.remove(element))
                    count++;
            }
        }
        System.out.println("_"+count+ " product(s) deleted by name \""+name+"\"\n");
    }

    public void displayProductList() {
        System.out.println("---------------------------------------");
        for (T element : officalList) {
            System.out.println(officalList.indexOf(element)+1+" "+element);
        }
        System.out.println("---------------------------------------");
        System.out.println();
    }

    public void sortByIncreasingPrice() {
        Collections.sort(officalList);
        System.out.println("-->the product list has been sorted by increasing price\n");
    }

    public void sortByDecreasingPrice() {
        officalList.sort(new Product());
        Collections.sort(officalList);
        Collections.reverse(officalList);
        System.out.println("-->the product list has been sorted by decreasing price\n");
    }

    public void sortByNameInAlphabeticalOrder() {
        officalList.sort(new Product());
 //       System.out.println("_the product list has been sorted by name in alphabetical order\n");
    }
    public void saveOfficalListAsFile() throws IOException {
        ObjectOutputStream objectOutputStream=
                new ObjectOutputStream(new FileOutputStream(objectFile));
        for (T element:
                officalList) {
            objectOutputStream.writeObject(element);
        }
        objectOutputStream.close();
    }
    public void discoverObjectFile() throws IOException, ClassNotFoundException {
        saveOfficalListAsFile();
        ObjectInputStream objectInputStream=
                new ObjectInputStream(new FileInputStream(objectFile));
        System.out.println("get product list from file:");
        for (int i = 0; i< officalList.size(); i++){
            System.out.println(objectInputStream.readObject());
        }
        System.out.println();
        objectInputStream.close();
    }
    public void saveToFinalFile() throws IOException, InterruptedException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("_do you want to save the current product list to final file now?");
        System.out.print("enter 1 to continue, enter 2 to exit the function:");
        String choise=scanner.nextLine();
        switch (choise){
            case "2": return;
            case "1":
                System.out.println(" ok let's go");
                break;
            default:System.out.println("###invalid input, try again:");
            saveToFinalFile();
        }
        Thread.sleep(1000);
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(finalFile));
        bufferedWriter.write("final list:"+"\n");
        for (Object o : officalList) {
            bufferedWriter.write(o.toString()+"\n");
        }
        bufferedWriter.close();
        System.out.println("saving completed");

    }
    public void sortProductList() throws InterruptedException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("you're about to sort the current product list");
        Thread.sleep(1000);
        System.out.println("enter 1 to sort by Name In Alphabetical Order");
        System.out.println("enter 2 to sort by Price In Ascending Order");
        System.out.println("enter 3 to exit the function");
        String choise=scanner.nextLine();
        System.out.println("_ok let's go");
        switch (choise){
            case"1":sortByNameInAlphabeticalOrder();break;
            case "2":sortByDecreasingPrice();break;
            case "3":return;
            default:System.out.println("###invalid input, try again:");
            sortProductList();
        }
    }
}