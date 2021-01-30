package com.lhf.designpatternlib.adapter.example;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by Joshua on 2021/1/30 16:46
 */
public class EnumerationIteratorTest {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        for (int i = 0; i < 10; i++) {
            hashtable.put("key" + i, "value" + i);
        }
        // 这可能是以前老的代码，使用的是Enumeration接口
        Enumeration<String> elements = hashtable.elements();
        EnumerationIterator<String> enumerationIterator = new EnumerationIterator<>(elements);
        // 但是新代码的方法，都是用的Iterator接口，用适配器EnumerationIterator，就可以很好的适配，不需要修改现有代码
        print(enumerationIterator);
    }

    public static void print(Iterator<String> iterator) {
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println("EnumerationIteratorTest.print: next = [" + next + "]");
        }
    }
}
