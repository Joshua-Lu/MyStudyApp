package com.lhf.designpatternlib.observer.example;

import java.util.Observable;

/**
 * 天气数据，可观察者，继承自java.util.Observable类
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
