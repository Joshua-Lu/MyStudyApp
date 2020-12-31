package com.lhf.designpatternlib.interpret;

/**
 * Created by Joshua on 2020/4/7 22:00.
 */
public class DivNode extends SymbolNode {
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return left.interpret() / right.interpret();
    }

    @Override
    public String toString() {
        return left + " / " + right;
    }
}
