import Annotations.Application;
import Annotations.CommandListner;


public class Main {

    public static void main(String[] args)  {

        CommandListner commandListner = new CommandListner();

        Application application = new Application(commandListner);

        application.run();

    }

}
