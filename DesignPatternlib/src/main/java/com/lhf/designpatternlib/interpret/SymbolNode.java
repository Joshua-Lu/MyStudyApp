package com.lhf.designpatternlib.interpret;

/**
 * 终结表达式抽象类
 * Created by Joshua on 2020/4/7 21:59.
 */
public abstract class SymbolNode implements Node {
    protected Node left;
    protected Node right;

    public SymbolNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}