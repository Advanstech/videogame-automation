package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigLoader;
import utils.ApiRequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthenticationService {
    private static final ConfigLoader config = ConfigLoader.getInstance();
    private static final String AUTH_ENDPOINT = "/authenticate";
    private static String authToken;

    // Singleton instance
    private static AuthenticationService instance;

    private AuthenticationService() {}

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    public String getAuthToken() {
        if (authToken == null) {
            authToken = authenticate();
        }
        return authToken;
    }

    private String authenticate() {
        Response response = given()
                .spec(ApiRequestSpecification.getDefaultRequestSpec())
                .body(createAuthRequestBody())
                .post(AUTH_ENDPOINT);

        if (response.getStatusCode() != 200) {
            String errorMessage = String.format("Authentication failed with status code: %d, response: %s",
                    response.getStatusCode(), response.getBody().asString());
            throw new RuntimeException(errorMessage);
        }

        return response.jsonPath().getString("token");
    }

    private String createAuthRequestBody() {
        String username = config.getProperty("api.auth.username");
        String password = config.getProperty("api.auth.password");

        if (username == null || password == null) {
            throw new RuntimeException("Username or password not found in configuration");
        }

        return String.format("""
                {
                    "username": "%s",
                    "password": "%s"
                }""", username, password);
    }
}
