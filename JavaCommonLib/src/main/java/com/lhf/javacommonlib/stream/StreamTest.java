package com.lhf.javacommonlib.stream;

import com.lhf.javacommonlib.common.Person;

import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * 使用 Stream 对集合进行操作
 * Created by Joshua on 2020/10/24 10:26
 */
public class StreamTest {

    /**
     * 通过 Stream.of 将数组转换成 Stream
     */
    @Test
    public void testArrayStream() {
        // Stream.of 可以传可变参数或数组
        Stream<String> stringStream = Stream.of("路飞", "索隆", "娜美");

        String[] names = {"路飞", "索隆", "娜美"};
        Stream<String> stringStream1 = Stream.of(names);
    }

    /**
     * 通过 Collection.stream() 将集合转换成 Stream
     */
    @Test
    public void testListStream() {
        ArrayList<String> list = new ArrayList<>();
        list.add("路飞");
        list.add("索隆");
        list.add("娜美");
        list.add("乌索普");
        list.add("山治");
        list.add("乔巴");
        list.add("罗宾");
        list.add("弗兰奇");
        list.add("布鲁克");
        list.add("甚平");

        list.stream()
                .filter(s -> s.length() == 3) // 过滤
                .map(Person::new) // 映射
                .skip(1) // 跳过
                .forEach(System.out::println);// 遍历
    }
}
