// src/main/java/utils/RequestSpecBuilder.java
package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class RequestSpecBuilder {
    public static RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getProperty("api.base.url"))
                .setContentType(ContentType.JSON)
                .build();
    }
}
