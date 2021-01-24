package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * Created by Joshua on 2021/1/24 17:06
 */
public class RemoteControllerTest {
    public static void main(String[] args) {
        RemoteController remoteController = new RemoteController();

        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        remoteController.setCommand(0, lightOnCommand, lightOffCommand);

        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
        GarageDoorCloseCommand garageDoorCloseCommand = new GarageDoorCloseCommand(garageDoor);
        remoteController.setCommand(1, garageDoorOpenCommand, garageDoorCloseCommand);

        Stereo stereo = new Stereo();
        StereoOnWithCdCommand stereoOnWithCdCommand = new StereoOnWithCdCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);
        remoteController.setCommand(2, stereoOnWithCdCommand, stereoOffCommand);
        System.out.println("RemoteControllerTest.main: remoteController = [" + remoteController + "]");

        System.out.println("=====================测试各个开关====================");
        remoteController.onButtonWasPressed(0);
        remoteController.onButtonWasPressed(1);
        remoteController.onButtonWasPressed(2);

        remoteController.offButtonWasPressed(0);
        remoteController.offButtonWasPressed(1);
        remoteController.offButtonWasPressed(2);

        System.out.println("======================测试位添加命令的默认执行===================");
        // 未设置命令的位置，调用默认命令NoCommand里的方法
        remoteController.offButtonWasPressed(3);

        System.out.println("======================测试撤销操作，无状态保存===================");
        remoteController.onButtonWasPressed(0);
        System.out.println("RemoteControllerTest.main: remoteController = [" + remoteController + "]");
        remoteController.undoButtonWasPressed();

        System.out.println("======================测试撤销操作，有状态保存===================");
        remoteController.offButtonWasPressed(2);
        remoteController.undoButtonWasPressed();

        System.out.println("======================测试宏命令===================");
        MacroCommand partyOn = new MacroCommand(new ICommand[]{lightOnCommand, garageDoorOpenCommand, stereoOnWithCdCommand});
        MacroCommand partyOff = new MacroCommand(new ICommand[]{lightOffCommand, garageDoorCloseCommand, stereoOffCommand});
        remoteController.setCommand(3, partyOn, partyOff);
        System.out.println("===================== 执行宏命令：partyOn ====================");
        remoteController.onButtonWasPressed(3);
        System.out.println("===================== 执行宏命令：partyOff ====================");
        remoteController.offButtonWasPressed(3);
        System.out.println("===================== 执行宏命令：撤销 ====================");
        remoteController.undoButtonWasPressed();

        System.out.println("======================测试多层次撤销===================");
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();
        remoteController.undoButtonWasPressed();

    }
}
