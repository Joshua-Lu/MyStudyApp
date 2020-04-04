package com.lhf.designpatternlib.prototype;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 深拷贝原型
 * 1.通过Serializable实现，成员变量对象类也要实现Serializable
 * 2.通过转成Json实现
 * Created by Joshua on 2020/4/2 23:32.
 */
public class DeepPrototype implements Serializable {

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

    public DeepPrototype() {
        System.out.println("DeepPrototype.DeepPrototype() called");
    }

    /**
     * 深拷贝，通过序列化方式
     */
    public DeepPrototype deepCloneBySerializable() {
        System.out.println("DeepPrototype.deepCloneBySerializable() called");
        DeepPrototype deepPrototype = null;
        try {
            // 写入字节流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            deepPrototype = (DeepPrototype) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deepPrototype;
    }

    /**
     * 深拷贝，通过序列化方式
     */
    public DeepPrototype deepCloneByJson() {
        System.out.println("DeepPrototype.deepCloneByJson() called");
        DeepPrototype deepPrototype = null;
        Gson gson = new Gson();
        String json = gson.toJson(this);
        deepPrototype = gson.fromJson(json, DeepPrototype.class);
        return deepPrototype;
    }

    @Override
    public String toString() {
        return "DeepPrototype{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
