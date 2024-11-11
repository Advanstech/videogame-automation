// src/main/java/services/AuthenticationService.java
package services;

import io.restassured.response.Response;
import utils.ConfigLoader;
import static io.restassured.RestAssured.given;

public class AuthenticationService {
    private static final String AUTH_ENDPOINT = "/api/authenticate";
    private String authToken;

    public String getAuthToken() {
        if (authToken == null) {
            authToken = authenticate();
        }
        return authToken;
    }

    private String authenticate() {
        Response response = given()
                .spec(RequestSpecBuilder.getDefaultRequestSpec())
                .body(createAuthRequestBody())
                .post(AUTH_ENDPOINT);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Authentication failed with status code: " + response.getStatusCode());
        }

        return response.jsonPath().getString("token");
    }

    private String createAuthRequestBody() {
        return String.format("""
                {
                    "username": "%s",
                    "password": "%s"
                }
                """,
                ConfigLoader.getInstance().getProperty("api.auth.username"),
                ConfigLoader.getInstance().getProperty("api.auth.password")
        );
    }
}
