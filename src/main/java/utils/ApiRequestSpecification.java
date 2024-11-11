package utils;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiRequestSpecification {

    private static final ConfigLoader configLoader = ConfigLoader.getInstance();

    public static RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(configLoader.getProperty("api.base.url"))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getAuthenticatedRequestSpec() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(configLoader.getProperty("api.auth.username"));
        authScheme.setPassword(configLoader.getProperty("api.auth.password"));

        return new RequestSpecBuilder()
                .setBaseUri(configLoader.getProperty("api.base.url"))
                .setContentType(ContentType.JSON)
                .setAuth(authScheme)
                .build();
    }

    public static RequestSpecification getUnauthenticatedRequestSpec() {
        return getDefaultRequestSpec();
    }
}