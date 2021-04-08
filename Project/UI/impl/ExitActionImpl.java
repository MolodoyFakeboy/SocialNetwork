package UI.impl;

import UI.IAction;

public class ExitActionImpl implements IAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
