package Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExProperties extends Properties {

    public void load(final File file) throws IOException {
        try (InputStream is = new FileInputStream(file)) {
            load(is);
        }
    }

    public boolean getBoolean(final String name, final boolean defaultValue) {
        boolean val = defaultValue;

        final String value;

        if ((value = super.getProperty(name, null)) != null)
            val = Boolean.parseBoolean(value);

        return val;
    }

    public String getString(final String name, final String defaultValue) {
        String val = defaultValue;

        final String value;

        if ((value = super.getProperty(name, null)) != null)
            return value;

        return val;
    }

    public int getInteger(final String name, final int defaultValue) {
        int val = defaultValue;

        final String value;

        if ((value = super.getProperty(name, null)) != null)
            val = Integer.parseInt(value);

        return val;
    }

}
