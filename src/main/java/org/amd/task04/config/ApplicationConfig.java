package org.amd.task04.config;

import java.util.ResourceBundle;

/**
 * Application config properties
 */
public class ApplicationConfig {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

    /**
     * Gets string property value by key.
     * @param key Key
     * @return String value
     */
    public static String getStrProperty(String key) {
        return bundle.getString(key);
    }

    /**
     * Gets string property value by key. Returns default value if property not found.
     * @param key Key
     * @param defaultValue Default value
     * @return String value
     */
    public static String getStrProperty(String key, String defaultValue) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }

        return defaultValue;
    }

    /**
     * Gets integer property value by key.
     * @param key Key
     * @return Integer value
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(bundle.getString(key));
    }

    /**
     * Gets integer property value by key.
     * @param key Key
     * @param defaultValue Default value
     * @return Integer value
     */
    public static int getIntProperty(String key, int defaultValue) {
        if (bundle.containsKey(key)) {
            return Integer.parseInt(bundle.getString(key));
        }

        return defaultValue;
    }
}
