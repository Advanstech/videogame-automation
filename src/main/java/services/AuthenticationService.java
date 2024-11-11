package services;

import io.restassured.response.Response;
import utils.ConfigLoader;
import utils.ApiRequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthenticationService {
    private static final String AUTH_ENDPOINT = ConfigLoader.getInstance().getProperty("api.endpoint.authenticate");
    private String authToken;

    public String getAuthToken() {
        if (authToken == null) {
            authToken = authenticate();
        }
        return authToken;
    }

    private String authenticate() {
        Response response = given()
                .spec(ApiRequestSpecification.getAuthenticatedRequestSpec())
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
        String username;
        String password;

        try {
            username = ConfigLoader.getInstance().getProperty("api.auth.username");
            password = ConfigLoader.getInstance().getProperty("api.auth.password");
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to retrieve authentication properties: " + e.getMessage(), e);
        }

        if (username == null || password == null) {
            throw new RuntimeException("Username or password not found in configuration");
        }

        return String.format("{\n" +
                "    \"username\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", username, password);
    }
}