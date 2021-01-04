package com.lhf.javacommonlib.xml;

import com.lhf.javacommonlib.common.Book;
import com.lhf.javacommonlib.common.Constants;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Joshua on 2021/1/4 23:57
 */
public class XmlSaxParseTest {
    @Test
    public void testSaxParse() {
        String fileName = Constants.FILE_XML_BOOK_SHELF;
        ArrayList<Book> books = saxParse(fileName);
        System.out.println(books);
    }

    private ArrayList<Book> saxParse(String fileName) {
        // 处理逻辑在ContentHandler中
        MyContentHandler contentHandler = new MyContentHandler();
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentHandler.getBooks();
    }

    static class MyContentHandler extends DefaultHandler {
        private ArrayList<Book> books;
        private Book book;
        private String tag;

        public ArrayList<Book> getBooks() {
            return books;
        }

        @Override
        public void startDocument() throws SAXException {
//                    System.out.println("文档开始");

        }

        @Override
        public void endDocument() throws SAXException {
//                    System.out.println("文档结束");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//                    System.out.println("开始标签：" + qName);
            tag = qName;
            switch (qName) {
                case Constants.XmlTag.BOOK_SHELF:
                    books = new ArrayList<>();
                    break;
                case Constants.XmlTag.BOOK:
                    book = new Book();
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
//                    System.out.println("结束标签：" + qName);
            if (Constants.XmlTag.BOOK.equals(qName)) {
                books.add(book);
            }
            tag = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
//                    System.out.println("文本内容：" + new String(ch, start, length));
            String content = new String(ch, start, length);
            if (tag != null) {
                switch (tag) {
                    case Constants.XmlTag.TITLE:
                        book.setTitle(content);
                        break;
                    case Constants.XmlTag.AUTHOR:
                        book.setAuthor(content);
                        break;
                    case Constants.XmlTag.PRICE:
                        book.setPrice(Integer.parseInt(content));
                        break;
                }
            }
        }
    }
}
