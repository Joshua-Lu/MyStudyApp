package com.lhf.designpatternlib.factory;

/**
 * 简单工厂模式
 * 该模式需通过向工厂传递类型来指定要创建的对象
 * Created by Joshua on 2020/3/28 20:48.
 */
public class SimpleFactory {

    public IProductA createProduct(String type) {
        switch (type) {
            case "A1":
                return new ProductA1();
            case "A2":
                return new ProductA2();
        }
        return null;
    }
}
