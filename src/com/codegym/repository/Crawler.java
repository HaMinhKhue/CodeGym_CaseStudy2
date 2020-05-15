package com.codegym.repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
    static void crawBooks() throws IOException {
        URL bookURL = new URL("https://www.nytimes.com/books/best-sellers/");
        Scanner scanner=new Scanner(new InputStreamReader(bookURL.openStream()));
        scanner.useDelimiter("\\\\Z");
        String content=scanner.next();
        scanner.close();
        content=content.replaceAll("\\n","");



        Matcher matcher= Pattern.compile
                ("role=\"presentation\" alt=\"(.*?) by (.*?)\" class=\"css-35otwa\"")
                .matcher(content);
        while (matcher.find()){
            System.out.println(matcher.group(1));
        }

    }

    public static void main(String[] args) throws IOException {
        crawBooks();
    }
}
