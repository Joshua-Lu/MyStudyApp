package com.lhf.designpatternlib.observer.example;

import java.util.Observable;
import java.util.Observer;

/**
 * 天气状况布告板，观察者，实现java.util.Observer
 * Created by Joshua on 2021/1/3 19:27
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private int temperature;
    private int humidity;
    private Observable weatherData;

    public CurrentConditionsDisplay(Observable weatherData) {
        this.weatherData = weatherData;
        this.weatherData.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData changedWeatherData = (WeatherData) o;
            System.out.println("CurrentConditionsDisplay.update: changedWeatherData = [" + changedWeatherData + "]");
            temperature = changedWeatherData.getTemperature();
            humidity = changedWeatherData.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay.display: temperature = [" + temperature + "], humidity = [" + humidity + "]");
    }
}
