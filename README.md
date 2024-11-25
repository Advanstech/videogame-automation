# Video Game API Automation Framework

## Overview
This framework provides automated testing for the Video Game API using Rest Assured and Java. It implements a robust testing structure for API validation, response verification, and end-to-end testing scenarios.

## Features
- REST API automation using Rest Assured
- Data-driven testing capabilities
- Detailed test reporting
- Modular framework structure
- Environment-specific configuration management

## Tech Stack
- Java 11 or higher
- Maven 3.6.3 or higher
- Rest Assured
- TestNG
- Git

## Project Structure
    VideoGameApiAutomation/
├── src/
│ ├── main/java/
│ │ ├── config/ # Configuration files
│ │ ├── endpoints/ # API endpoints
│ │ ├── models/ # POJO classes
│ │ └── utils/ # Utility classes
│ └── test/java/
│ └── tests/ # Test classes
├── resources/
│ ├── testdata/ # Test data files
│ └── config.properties # Configuration properties
├── pom.xml
└── README.md

## Setup Instructions
1. Clone the repository
2. Install dependencies using Maven
3. Configure environment variables

## Code Implementation

# Video Game API Automation Framework

## Overview
This framework provides automated testing for the Video Game API using Rest Assured and Java. It implements a robust testing structure for API validation, response verification, and end-to-end testing scenarios.

## Features
- REST API automation using Rest Assured
- Data-driven testing capabilities
- Detailed test reporting
- Modular framework structure
- Environment-specific configuration management

## Tech Stack
- Java 11 or higher
- Maven 3.6.3 or higher
- Rest Assured
- TestNG
- Git

## Project Structure
VideoGameApiAutomation/
├── src/
│ ├── main/java/
│ │ ├── config/ # Configuration files
│ │ ├── endpoints/ # API endpoints
│ │ ├── models/ # POJO classes
│ │ └── utils/ # Utility classes
│ └── test/java/
│ └── tests/ # Test classes
├── resources/
│ ├── testdata/ # Test data files
│ └── config.properties # Configuration properties
├── pom.xml
└── README.md


## Setup Instructions
1. Clone the repository
2. Install dependencies using Maven
3. Configure environment variables

## Code Implementation

### 1. pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.videogame.api</groupId>
    <artifactId>video-game-api-automation</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <rest-assured.version>5.3.0</rest-assured.version>
        <testng.version>7.7.1</testng.version>
        <jackson.version>2.15.2</jackson.version>
        <lombok.version>1.18.28</lombok.version>
        <log4j.version>2.20.0</log4j.version>
    </properties>

    <dependencies>
        <!-- REST Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${rest-assured.version}</version>
        </dependency>

        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
        </dependency>

        <!-- Jackson -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>

        <!-- Log4j -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>${log4j.version}</version>
        </dependency>
    </dependencies>
</project>

2. Configuration Manager (ConfigManager.java)
package com.videogame.api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();
    private static ConfigManager instance;

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            String env = System.getProperty("env", "test");
            FileInputStream file = new FileInputStream(
                    "src/test/resources/config/" + env + "_config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Configuration file not found", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getApiKey() {
        return getProperty("api.key");
    }
}

3. Video Game Model (VideoGame.java)
package com.videogame.api.models;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class VideoGame {
    private Integer id;
    private String name;
    private String releaseDate;
    private Integer reviewScore;
    private String category;
    private String rating;
}

4. Video Game Endpoint (VideoGameEndpoint.java)
package com.videogame.api.endpoints;

import com.videogame.api.config.ConfigManager;
import com.videogame.api.models.VideoGame;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class VideoGameEndpoint {
    private static final String BASE_PATH = "/api/v1/videogames";
    private final ConfigManager config = ConfigManager.getInstance();

    public Response getAllVideoGames() {
        return given()
                .header("Accept", "application/json")
                .when()
                .get(config.getBaseUrl() + BASE_PATH);
    }

    public Response getVideoGameById(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get(config.getBaseUrl() + BASE_PATH + "/" + id);
    }

    public Response createVideoGame(VideoGame videoGame) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(videoGame)
                .when()
                .post(config.getBaseUrl() + BASE_PATH);
    }

    public Response updateVideoGame(int id, VideoGame videoGame) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(videoGame)
                .when()
                .put(config.getBaseUrl() + BASE_PATH + "/" + id);
    }

    public Response deleteVideoGame(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                .delete(config.getBaseUrl() + BASE_PATH + "/" + id);
    }
}

5. Test Utilities (TestUtils.java)
package com.videogame.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T loadTestData(String filePath, Class<T> valueType) {
        try {
            return objectMapper.readValue(new File(filePath), valueType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static String generateRandomString(int length) {
        return java.util.UUID.randomUUID().toString().substring(0, length);
    }
}

6. Test Class (VideoGameTest.java)
package com.videogame.api.tests;

import com.videogame.api.endpoints.VideoGameEndpoint;
import com.videogame.api.models.VideoGame;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class VideoGameTest {
    private VideoGameEndpoint videoGameEndpoint;

    @BeforeClass
    public void setup() {
        videoGameEndpoint = new VideoGameEndpoint();
    }

    @Test
    public void testGetAllVideoGames() {
        Response response = videoGameEndpoint.getAllVideoGames();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testCreateAndGetVideoGame() {
        VideoGame newGame = VideoGame.builder()
                .name("Super Mario")
                .releaseDate("2023-08-15")
                .reviewScore(95)
                .category("Platform")
                .rating("E")
                .build();

        Response createResponse = videoGameEndpoint.createVideoGame(newGame);
        assertEquals(createResponse.getStatusCode(), 201);

        int gameId = createResponse.jsonPath().getInt("id");
        Response getResponse = videoGameEndpoint.getVideoGameById(gameId);
        assertEquals(getResponse.getStatusCode(), 200);
        assertEquals(getResponse.jsonPath().getString("name"), "Super Mario");
    }
}

7. Configuration File (test_config.properties)
base.url=http://localhost:8080
api.key=your_api_key
environment=test

8. TestNG XML Configuration (testng.xml)
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Video Game API Test Suite">
    <test name="API Tests">
        <classes>
            <class name="com.videogame.api.tests.VideoGameTest"/>
        </classes>
    </test>
</suite>



Running Tests
1. Run all tests:
mvn test

2. Run specific test suite:
mvn test -Dsuite=<suite-name>

3. Run tests with specific environment:
mvn test -Denv=staging


Test Reports
Test reports are generated in the target/surefire-reports directory

HTML reports can be found at target/surefire-reports/index.html

Best Practices
Always update test data before pushing changes

Follow the existing project structure for new additions

Add appropriate comments and documentation

Create new branches for feature development

Write meaningful commit messages

Common Issues and Solutions
Connection Issues

Verify API base URL in config

Check network connectivity

Ensure API key is valid

Test Data Issues

Verify test data exists in the correct format

Check file paths in the code

Maintenance Guidelines
Regular dependency updates

Code review process

Documentation updates

Test data cleanup

Regular framework enhancements

Support
For any issues or questions:

Check existing documentation

Review test logs

Contact the team lead or project maintainer

Future Enhancements
Add more test scenarios

Implement parallel test execution

Enhance reporting features

Add performance testing capabilities

This README provides a complete guide with:
- Project structure
- All necessary code
- Setup instructions
- Running instructions
- Best practices
- Maintenance guidelines
- Future enhancements

A developer can copy this entire content into their README.md file and have a fully documented project structure with all the necessary code implementations.
