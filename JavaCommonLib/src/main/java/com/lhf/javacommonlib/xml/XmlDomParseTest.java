package com.lhf.javacommonlib.xml;

import com.lhf.javacommonlib.common.Constants;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Dom解析XML文件
 * 缺点：一次性将整个文件加载到内存，文件大时，容易发生内存溢出
 * 优点：不仅可以解析，还能修改xml
 * <p>
 * Created by Joshua on 2021/1/4 21:56
 */
public class XmlDomParseTest {

    @Test
    public void testDomParse() {
        String fileName = Constants.FILE_XML_BOOK_SHELF;
        int index = 1;
        String bookTag = Constants.XmlTag.PRICE;
        domParse(fileName, index, bookTag);
    }

    /**
     * Dom解析XML文件
     */
    private void domParse(String fileName, int index, String bookTag) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // document 就是解析后的整个文件内容，通过document的相关方法就能获取到所有需要的内容
            Document document = documentBuilder.parse(fileName);

            NodeList nodeList = document.getElementsByTagName(bookTag);
            Node node = nodeList.item(index);
            System.out.println("第" + index + "本书的" + bookTag + "是：" + node.getTextContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDomModify() {
        String fileName = Constants.FILE_XML_BOOK_SHELF;
        int index = 1;
        String bookTag = Constants.XmlTag.PRICE;
        String value = "98";
        domModify(fileName, index, bookTag, value);
    }

    /**
     * Dom修改XML文件
     */
    private void domModify(String fileName, int index, String bookTag, String value) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // document 就是解析后的整个文件内容，通过document的相关方法就能获取到所有需要的内容
            Document document = documentBuilder.parse(fileName);

            // 获取对应Node并修改值
            NodeList nodeList = document.getElementsByTagName(bookTag);
            Node node = nodeList.item(index);
            System.out.println("修改前：第" + index + "本书的" + bookTag + "是：" + node.getTextContent());
            node.setTextContent(value);
            System.out.println("修改后：第" + index + "本书的" + bookTag + "是：" + node.getTextContent());

            // 写回文件里
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(fileName));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
