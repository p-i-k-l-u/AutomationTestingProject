package com.framework.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static void loadProperties() {

        try {

            prop = new Properties();

            FileInputStream fis =
                    new FileInputStream(
                    System.getProperty("user.dir")
                    + "/src/main/resources/config.properties"
                    );

            prop.load(fis);

            System.out.println(
                    "Config File Loaded"
            );
        }

        catch(Exception e) {

            System.out.println(
                    "Config File NOT Found"
            );

            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        return prop.getProperty(key);
    }
}