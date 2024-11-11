// src/main/java/utils/TestDataLoader.java
package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class TestDataLoader {
    private final ObjectMapper objectMapper;

    public TestDataLoader() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T loadTestData(String fileName, Class<T> valueType) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new RuntimeException("Test data file not found: " + fileName);
            }
            return objectMapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data from " + fileName, e);
        }
    }
}
