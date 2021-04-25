package src.main.java;

import src.main.java.Modules.Application;
import src.main.java.Modules.CommandListner;

public class Main {

    public static void main(String[] args)  {

        CommandListner commandListner = new CommandListner();

        Application application = new Application(commandListner);

        application.run();

    }

}
