package command;

/**
 * Created by abc on 2015/7/2.
 */
public class RemoteControl {
    Command commandOn[];
    Command commandOff[];

    public RemoteControl(){
        commandOn=new Command[7];
        commandOff=new Command[7];
    }

    public void setCommand(int pos,Command on,Command off){
        commandOn[pos]=on;
        commandOff[pos]=off;
    }

    public void printOn(int pos){
        commandOn[pos].execute();
    }

    public void printOff(int pos){
        commandOff[pos].execute();
    }
}
