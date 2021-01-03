package com.lhf.designpatternlib.observer.example;

import java.util.Observable;

/**
 * 天气数据，可观察者，继承自java.util.Observable类
 * <p>
 * 使用Java内置Observable类，相比自定义接口Observable实现的优缺点：
 * 优点：不用自己实现addObserver、deleteObserver、notifyObservers等方法，使用简便
 * 缺点：1.java.util.Observable类是一个类，不是接口，使用必须继承该类，Java又是单继承，导致无法继承其他类
 * 2.java.util.Observable有些关键方法，如setChanged是protected的，导致只能使用继承，无法使用组合，来使用Observable
 * <p>
 * Created by Joshua on 2021/1/3 18:04
 */
public class WeatherData extends Observable {
    private int temperature;
    private int humidity;
    private int pressure;

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void measurementsChanged() {
        System.out.println("WeatherData.measurementsChanged() called");
        setChanged();// 必须调用该方法，否则调用notifyObservers()无效
        notifyObservers();
    }

    public void setMeasurements(int temperature, int humidity, int pressure) {
        System.out.println("WeatherData.setMeasurements() called with: temperature = [" + temperature + "], humidity = [" + humidity + "], pressure = [" + pressure + "]");
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
