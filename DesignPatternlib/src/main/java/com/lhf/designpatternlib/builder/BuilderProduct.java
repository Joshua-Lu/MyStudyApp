package com.lhf.designpatternlib.builder;

/**
 * 包含多个组成部件的复杂对象，由具体建造者来创建其各个部件
 * Created by Joshua on 2020/3/29 15:08.
 */
public class BuilderProduct {
    private String partA;
    private String partB;
    private String partC;

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    public void show() {
        System.out.println("BuilderProduct.show: toString() = [" + toString() + "]");
    }

    @Override
    public String toString() {
        return "BuilderProduct{" +
                "partA='" + partA + '\'' +
                ", partB='" + partB + '\'' +
                ", partC='" + partC + '\'' +
                '}';
    }
}
