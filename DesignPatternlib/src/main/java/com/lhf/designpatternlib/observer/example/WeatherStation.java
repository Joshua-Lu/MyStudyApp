package com.lhf.designpatternlib.observer.example;

/**
 * Created by Joshua on 2021/1/3 19:49
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(20, 40, 1);
    }
}
