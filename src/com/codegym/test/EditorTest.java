package com.codegym.test;

import com.codegym.controller.BookManager;
import com.codegym.controller.Editor;
import com.codegym.model.Book;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EditorTest {

    @Test
    public void getBookByID() throws IOException {
        int ID=1;
        Book expected=null;
        Object actual=new Editor<>().getBookByID(4454,new BookManager(BookManager.FINAL_BOOK_LIST_FILE_PATH,/*BookManager.BOOK_OBJECTS_FILE_PATH,*/BookManager.ADDICTIONAL_BOOK_LIST_FILE_PATH,BookManager.CRAWEDBOOKLIST_FILE_PATH));
        assertEquals(expected,actual);
    }
}