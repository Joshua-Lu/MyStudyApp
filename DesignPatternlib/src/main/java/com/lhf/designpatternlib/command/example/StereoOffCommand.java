package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 音响关闭命令
 * <p>
 * Created by Joshua on 2021/1/24 17:54
 */
public class StereoOffCommand implements ICommand {

    Stereo stereo;
    String prePlayType;
    int preVolume;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        prePlayType = stereo.getPlayType();
        preVolume = stereo.getVolume();
        System.out.println("StereoOffCommand.execute 音响关闭前: prePlayType = [" + prePlayType + "], preVolume = [" + preVolume + "]");
        stereo.off();
    }

    @Override
    public void undo() {
        System.out.println("StereoOffCommand.undo() called 撤销音响关闭操作");
        stereo.on();
        switch (prePlayType) {
            case "cd":
                stereo.setCd();
                break;
            case "dvd":
                stereo.setDvd();
                break;
            case "radio":
                stereo.setRadio();
                break;
        }
        stereo.setVolume(preVolume);
    }
}
