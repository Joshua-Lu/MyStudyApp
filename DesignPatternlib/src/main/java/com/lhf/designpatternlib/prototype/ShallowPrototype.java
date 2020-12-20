package com.lhf.designpatternlib.prototype;

/**
 * 浅拷贝原型
 * 直接使用Object的clone方法
 * 对于对象成员变量无法进行拷贝，除非该对象也实现了clone
 * Created by Joshua on 2020/4/2 23:32.
 */
public class ShallowPrototype implements Cloneable {

    private String name;
    private int age;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShallowPrototype() {
        System.out.println("ShallowPrototype.ShallowPrototype() called");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("ShallowPrototype.clone() called");
        return super.clone();
    }

    @Override
    public String toString() {
        return "ShallowPrototype[" + super.toString() + "]{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
