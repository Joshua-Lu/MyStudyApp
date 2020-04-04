package com.lhf.designpatternlib.flyweight;

/**
 * Created by  on 2020/4/4.
 */
public class UnsharedConcreteFlyweight {
    private String info;

    public UnsharedConcreteFlyweight(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UnsharedConcreteFlyweight{" +
                "info='" + info + '\'' +
                '}';
    }
}
