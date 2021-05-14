package org.project;

import org.project.AnnotationHandler.Application;
import org.project.AnnotationHandler.CommandListner;

public class Main {

    public static void main(String[] args)  {
        CommandListner commandListner = new CommandListner();

        Application application = new Application(commandListner);

        application.run();
    }
}
