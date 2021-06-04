package org.project.Util;

import org.project.Annotations.InjectProperty;

import java.io.File;

public class Prop {

    private static ExProperties properties;


    @InjectProperty
    public void load() {
        properties = initProperties("src/main/resources/config.properties");
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

}
