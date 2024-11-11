package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import utils.ConfigLoader;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static ConfigLoader configLoader;

    @BeforeAll
    public static void setup() {
        // Initialize test configuration
        configLoader = ConfigLoader.getInstance();

        // Set up RestAssured base configuration
        RestAssured.baseURI = configLoader.getProperty("api.base.url");

        // Build default request specification
    }
}