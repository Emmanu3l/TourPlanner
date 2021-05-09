package businesslayer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    public static String GetConfigProperty(String propertyName) {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        //InputStream inputStream = ConfigurationManager.class.getResourceAsStream(propFileName);
        InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream(propFileName);

        // wenn etwas gefunden wurde
        if (inputStream != null) {
            try {
                prop.load(inputStream);
                return prop.getProperty(propertyName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // return empty string
        return "";
    }

}
