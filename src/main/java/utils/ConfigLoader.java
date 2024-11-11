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
            // First try to load test config if available
            InputStream testConfig = getClass().getClassLoader()
                    .getResourceAsStream("test-config.properties");

            if (testConfig != null) {
                properties.load(testConfig);
            } else {
                // Fall back to main config if test config is not available
                InputStream mainConfig = getClass().getClassLoader()
                        .getResourceAsStream("config.properties");
                if (mainConfig != null) {
                    properties.load(mainConfig);
                }
            }

            // Override with environment variables if they exist
            loadEnvironmentVariables();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    private void loadEnvironmentVariables() {
        // First try to load from .env file
        String username = EnvLoader.getEnvVariable("API_USERNAME");
        String password = EnvLoader.getEnvVariable("API_PASSWORD");

        // If .env values are null, try system environment variables
        if (username == null) username = System.getenv("API_USERNAME");
        if (password == null) password = System.getenv("API_PASSWORD");

        // Only override if environment variables are present
        if (username != null) properties.setProperty("api.auth.username", username);
        if (password != null) properties.setProperty("api.auth.password", password);
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
