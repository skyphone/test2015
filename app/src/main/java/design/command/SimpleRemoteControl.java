package design.command;

/**
 * Created by abc on 2015/7/2.
 */
public class SimpleRemoteControl {
    Command command;
    public void setCommand(Command command){
        this.command=command;
    }

    public void buttonPress(){
        command.execute();
    }
}
