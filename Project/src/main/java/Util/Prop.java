package src.main.java.Util;


import src.main.java.Annotations.ConfigProperty;
import src.main.java.Annotations.InjectProperty;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class Prop {

    private static ExProperties properties;
    private static ExProperties properties2;


    @InjectProperty
    private String log4jConfPath;

    @ConfigProperty
    public void load() {
        properties = initProperties("Project/src/main/resources/config.properties");
        properties2 = initProperties("Project/src/main/resources/log4j.properties");
    }

    public void configLog4j(){
        PropertyConfigurator.configure(log4jConfPath);
    }

    public static ExProperties initProperties(String filename) {
        final ExProperties result = new ExProperties();

        try {
            result.load(new File(filename));
        } catch (Exception e) {
            System.out.println("An error occured loading '" + filename + "' properties.");
        }

        return result;
    }

    public static ExProperties getProperties() {
        return properties;
    }

    public static ExProperties getProperties2() {
        return properties2;
    }


}
