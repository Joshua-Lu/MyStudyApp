package com.lhf.designpatternlib.interpret;

/**
 * 非终结表达式
 * Created by Joshua on 2020/4/7 21:57.
 */
public class ValueNode implements Node {
    private int value;

    public ValueNode(int value) {
        this.value = value;
    }

    public int interpret() {
        return this.value;
    }
}
