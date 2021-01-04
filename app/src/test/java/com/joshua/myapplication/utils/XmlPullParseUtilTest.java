package com.joshua.myapplication.utils;

import com.lhf.javacommonlib.common.Book;
import com.lhf.javacommonlib.common.Constants;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Joshua on 2021/1/5 1:10
 */
public class XmlPullParseUtilTest {

    @Test
    public void pullParse() {
        ArrayList<Book> books = XmlPullParseUtil.pullParse(Constants.FILE_XML_BOOK_SHELF);
        System.out.println(books);
    }
}