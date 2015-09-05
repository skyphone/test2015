package command.one;

import command.Command;

/**
 * Created by abc on 2015/7/2.
 */
public class GarageDoorCommand implements Command{
    GarageDoor garageDoor;

    @Override
    public void execute() {
        garageDoor.lightOn();
    }

    public GarageDoorCommand(GarageDoor garageDoor){
        garageDoor=garageDoor;
    }
}
