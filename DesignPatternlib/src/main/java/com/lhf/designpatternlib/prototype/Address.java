package com.lhf.designpatternlib.prototype;

import java.io.Serializable;

/**
 * Created by Joshua on 2020/4/4.
 */
public class Address implements Serializable {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}
