package design.command;

/**
 * Created by abc on 2015/7/2.
 */
public class LightOnCommand implements Command{
    Light light;

    public LightOnCommand(Light light){

    }
    @Override
    public void execute() {
        light.on();
    }
}
