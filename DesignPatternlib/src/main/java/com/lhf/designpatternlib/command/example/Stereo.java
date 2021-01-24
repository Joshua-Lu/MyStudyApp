package com.lhf.designpatternlib.command.example;

/**
 * 音响
 * <p>
 * Created by Joshua on 2021/1/24 17:49
 */
public class Stereo {

    private String playType;
    private int volume;

    public void on() {
        System.out.println("Stereo.on() called 音响打开");
    }

    public void off() {
        System.out.println("Stereo.off() called 音响关闭");
    }

    public void setCd() {
        System.out.println("Stereo.setCd() called");
        playType = "cd";
    }

    public void setDvd() {
        System.out.println("Stereo.setDvd() called");
        playType = "dvd";
    }

    public void setRadio() {
        System.out.println("Stereo.setRadio() called");
        playType = "radio";
    }

    public void setVolume(int volume) {
        System.out.println("Stereo.setVolume() called with: volume = [" + volume + "]");
        this.volume = volume;
    }

    public String getPlayType() {
        return playType;
    }

    public int getVolume() {
        return volume;
    }
}
