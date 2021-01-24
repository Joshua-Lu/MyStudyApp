package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 音响打开播放CD命令
 * <p>
 * Created by Joshua on 2021/1/24 17:54
 */
public class StereoOnWithCdCommand implements ICommand {

    Stereo stereo;

    public StereoOnWithCdCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        // 可以执行一组操作
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {
        System.out.println("StereoOnWithCdCommand.undo() called 撤销音响打开操作");
        stereo.off();
    }
}
