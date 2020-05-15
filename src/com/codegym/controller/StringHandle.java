package com.codegym.controller;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringHandle {
    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("[đĐ]","d");
    }

    public static void main(String[] args) {
        System.out.println(StringHandle.removeAccent("Đại Nam Thực Lục (tái bản 2020)").toLowerCase().replaceAll(" ","").matches("(.*?)dainamthucluc(.*?)"));
    }

}