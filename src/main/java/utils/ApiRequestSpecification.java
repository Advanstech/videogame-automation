package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import services.AuthenticationService;

public class ApiRequestSpecification {
    private static final ConfigLoader configLoader = ConfigLoader.getInstance();
    private static final AuthenticationService authService = AuthenticationService.getInstance();

    // Returns a default request specification with base URI and content type set
    public static RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(configLoader.getProperty("api.base.url"))
                .setContentType(ContentType.JSON)
                .build();
    }

    // Returns an authenticated request specification with an Authorization header
    public static RequestSpecification getAuthenticatedRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(configLoader.getProperty("api.base.url"))
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + authService.getAuthToken())
                .build();
    }

    // Returns an unauthenticated request specification, which is the same as the default
    public static RequestSpecification getUnauthenticatedRequestSpec() {
        return getDefaultRequestSpec();
    }
}