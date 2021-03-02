package com.lhf.designpatternlib.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 * 将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，使其在不改变数据结构的前提下可以添加作用于这些元素的新的操作，为数据结构中的每个元素提供多种访问方式。
 * 它将对数据的操作与数据结构进行分离，是行为类模式中最复杂的一种模式。
 * Created by Joshua on 2020/4/6 22:56.
 */
public class ElementStructure {

    private List<IElement> list = new ArrayList<>();

    public void accept(IVisitor visitor) {
        for (IElement iElement : list) {
            iElement.accept(visitor);
        }
    }

    public void add(IElement element) {
        list.add(element);
    }

    public void remove(IElement element) {
        list.remove(element);
    }
}
