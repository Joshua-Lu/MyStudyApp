package com.joshua.myapplication.utils;

import com.lhf.javacommonlib.common.Book;
import com.lhf.javacommonlib.common.Constants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by Joshua on 2021/1/5 1:05
 */
public class XmlPullParseUtil {

    public static void main(String[] args) {
        ArrayList<Book> books = XmlPullParseUtil.pullParse(Constants.FILE_XML_BOOK_SHELF);
        System.out.println(books);
    }

    public static ArrayList<Book> pullParse(String fileName) {
        ArrayList<Book> books = null;
        Book book = null;
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlPullParserFactory.newPullParser();

            parser.setInput(new FileInputStream(fileName), "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (name != null) {
                            String content = parser.nextText();
                            switch (name) {
                                case Constants.XmlTag.BOOK_SHELF:
                                    books = new ArrayList<>();
                                    break;
                                case Constants.XmlTag.BOOK:
                                    book = new Book();
                                    break;
                                case Constants.XmlTag.TITLE:
                                    if (book != null) {
                                        book.setTitle(content);
                                    }
                                    break;
                                case Constants.XmlTag.AUTHOR:
                                    if (book != null) {
                                        book.setAuthor(content);
                                    }
                                    break;
                                case Constants.XmlTag.PRICE:
                                    if (book != null) {
                                        book.setPrice(Integer.parseInt(content));
                                    }
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (Constants.XmlTag.BOOK.equals(name)) {
                            if (books != null) {
                                books.add(book);
                            }
                        }
                        break;
                }

                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
