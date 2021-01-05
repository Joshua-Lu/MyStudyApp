package com.joshua.myapplication.utils;

import android.util.Log;
import android.util.Xml;

import com.lhf.javacommonlib.common.Book;
import com.lhf.javacommonlib.common.Constants;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Pull解析XML文件
 * Created by Joshua on 2021/1/5 1:05
 */
public class XmlPullParseUtil {

    private static final String TAG = "XmlPullParseUtil";

    public static ArrayList<Book> pullParse(File file) {
        ArrayList<Book> books = null;
        Book book = null;
        try {
            // 获取XmlPullParser对象，要在Android环境下运行，否则会报错（除非额外导入对应的包）
            // 方式一
//            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = xmlPullParserFactory.newPullParser();
            // 方式二
            XmlPullParser parser = Xml.newPullParser();

            Log.d(TAG, "pullParse: file.getAbsolutePath() = [" + file.getAbsolutePath() + "]");
            parser.setInput(new FileInputStream(file), "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (name != null) {
                            switch (name) {
                                case Constants.XmlTag.BOOK_SHELF:
                                    books = new ArrayList<>();
                                    break;
                                case Constants.XmlTag.BOOK:
                                    book = new Book();
                                    break;
                                case Constants.XmlTag.TITLE:
                                    if (book != null) {
                                        book.setTitle(parser.nextText());
                                    }
                                    break;
                                case Constants.XmlTag.AUTHOR:
                                    if (book != null) {
                                        book.setAuthor(parser.nextText());
                                    }
                                    break;
                                case Constants.XmlTag.PRICE:
                                    if (book != null) {
                                        book.setPrice(Integer.parseInt(parser.nextText()));
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
