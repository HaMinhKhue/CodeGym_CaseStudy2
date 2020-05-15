package com.codegym.test;

import com.codegym.controller.StringHandle;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringHandleTest {
    @Test
    public void removeAccentTest(){
        String inputString="Đại Nam Thực Lục";
        String expected="dai Nam Thuc Luc";
        String actual=StringHandle.removeAccent(inputString);
        assertEquals(expected, actual);
    }
}