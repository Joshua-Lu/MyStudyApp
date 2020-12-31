package com.lhf.designpatternlib.interpret;

import java.util.Stack;

/**
 * 解释器模式
 * 给分析对象定义一个语言，并定义该语言的文法表示，再设计一个解析器来解释语言中的句子。
 * 也就是说，用编译语言的方式来分析应用中的实例。
 * 这种模式实现了文法表达式处理的接口，该接口解释一个特定的上下文。
 * Created by Joshua on 2020/4/7 22:01.
 */
public class Calculator {
    private String statement;
    private Node node;

    public void build(String statement) {
        System.out.println("Calculator.build() called with: statement = [" + statement + "]");
        Node left = null, right = null;
        Stack stack = new Stack();
        String[] statementArr = statement.split(" ");
        for (int i = 0; i < statementArr.length; i++) {
            if (statementArr[i].equalsIgnoreCase("*")) {
                left = (Node) stack.pop();
                int val = Integer.parseInt(statementArr[++i]);
                right = new ValueNode(val);
                stack.push(new MulNode(left, right));
            } else if (statementArr[i].equalsIgnoreCase("/")) {
                left = (Node) stack.pop();
                int val = Integer.parseInt(statementArr[++i]);
                right = new ValueNode(val);
                stack.push(new DivNode(left, right));
            } else if (statementArr[i].equalsIgnoreCase("%")) {
                left = (Node) stack.pop();
                int val = Integer.parseInt(statementArr[++i]);
                right = new ValueNode(val);
                stack.push(new ModNode(left, right));
            } else {
                stack.push(new ValueNode(Integer.parseInt(statementArr[i])));
            }
        }
        this.node = (Node) stack.pop();
        System.out.println("Calculator.build result: node = [" + node + "]");
    }

    public int compute() {
        return node.interpret();
    }
}
