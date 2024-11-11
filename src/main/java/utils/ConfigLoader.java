package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader instance;
    private final Properties properties;

    private ConfigLoader() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try {
            // Load configuration from properties file
            InputStream configStream = getClass().getClassLoader()
                    .getResourceAsStream("config.properties");

            if (configStream != null) {
                properties.load(configStream);
            } else {
                throw new RuntimeException("Configuration file not found");
            }

            // Override with environment variables if they exist
            loadEnvironmentVariables();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    private void loadEnvironmentVariables() {
        // Load from system environment variables
        String apiBaseUrl = System.getenv("API_BASE_URL");
        String apiAuthToken = System.getenv("API_AUTH_TOKEN");

        // Only override if environment variables are present
        if (apiBaseUrl != null) properties.setProperty("api.base.url", apiBaseUrl);
        if (apiAuthToken != null) properties.setProperty("api.auth.token", apiAuthToken);
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property " + key + " not found in configuration");
        }
        return value;
    }
}