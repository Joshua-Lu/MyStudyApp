package com.lhf.javacommonlib.common;

/**
 * Created by Joshua on 2021/1/4 22:09
 */
public interface Constants {

    String FILE_ROOT_PATH = "src\\main\\java\\com\\lhf\\javacommonlib\\file\\";
    String FILE_XML_BOOK_SHELF = FILE_ROOT_PATH + "bookShelf.xml";

    interface XmlTag {
        String BOOK_SHELF = "bookShelf";
        String BOOK = "book";
        String TITLE = "title";
        String AUTHOR = "author";
        String PRICE = "price";
    }

    interface Url {
        String BaiDu = "https://www.baidu.com/";
    }
}
