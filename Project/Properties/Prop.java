package Properties;

import java.io.File;

public final class Prop {

    private static ExProperties properties;
    private static ExProperties properties2;

    public static void load() {
        properties = initProperties("src/Resources/config.properties");
        properties2 = initProperties("src/Resources/log4j.properties");
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
