package design.command.two;

import design.command.Command;

/**
 * Created by abc on 2015/7/2.
 */
public class WindowCommand implements Command {
    Window window;
    @Override
    public void execute() {
        window.color();
    }
    public WindowCommand(Window window){
        this.window=window;
    }
}
