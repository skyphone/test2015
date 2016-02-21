package design.command;

import design.command.one.GarageDoor;
import design.command.one.GarageDoorCommand;
import design.command.two.Window;
import design.command.two.WindowCommand;

/**
 * Created by abc on 2015/7/2.
 */
public class Test {
    public static void main(){
        //init light
        Light light=new Light();
        LightOnCommand lightOnCommand=new LightOnCommand(light);
        //init door
        GarageDoor garageDoor=new GarageDoor();
        GarageDoorCommand garageDoorCommand=new GarageDoorCommand(garageDoor);
        //init window
        Window window=new Window();
        WindowCommand windowCommand=new WindowCommand(window);


        //
        SimpleRemoteControl simpleRemoteControl=new SimpleRemoteControl();

        simpleRemoteControl.setCommand(lightOnCommand);
        simpleRemoteControl.buttonPress();

        simpleRemoteControl.setCommand(garageDoorCommand);
        simpleRemoteControl.buttonPress();

        simpleRemoteControl.setCommand(windowCommand);
        simpleRemoteControl.buttonPress();

        //
        RemoteControl remoteControl=new RemoteControl();
      //  remoteControl.setCommand();



    }



}
