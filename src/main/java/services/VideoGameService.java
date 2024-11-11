// src/main/java/services/VideoGameService.java
package services;

import io.restassured.response.Response;
import models.VideoGame;
import models.VideoGameRequest;
import utils.ConfigLoader;
import utils.RequestSpecBuilder;

import static io.restassured.RestAssured.given;

public class VideoGameService {
    private final String basePath;

    public VideoGameService() {
        this.basePath = ConfigLoader.getInstance().getProperty("api.videogames.path");
    }

    public Response createVideoGame(VideoGameRequest request) {
        return given()
                .spec(RequestSpecBuilder.getDefaultRequestSpec())
                .body(request)
                .post(basePath);
    }

    public Response getVideoGameById(int id) {
        return given()
                .spec(RequestSpecBuilder.getDefaultRequestSpec())
                .get(basePath + "/" + id);
    }

    public Response getAllVideoGames() {
        return given()
                .spec(RequestSpecBuilder.getDefaultRequestSpec())
                .get(basePath);
    }
}
