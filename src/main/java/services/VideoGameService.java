package services;

import io.restassured.response.Response;
import models.VideoGameRequest;
import utils.ConfigLoader;
import utils.ApiRequestSpecification;

import static io.restassured.RestAssured.given;

public class VideoGameService {
    private final String basePath;

    public VideoGameService() {
        this.basePath = ConfigLoader.getInstance().getProperty("api.videogames.path");
    }

    public Response createVideoGame(VideoGameRequest request) {
        return given()
                .spec(ApiRequestSpecification.getAuthenticatedRequestSpec())
                .body(request)
                .post(basePath);
    }

    public Response getVideoGameById(int id) {
        return given()
                .spec(ApiRequestSpecification.getDefaultRequestSpec())
                .get(basePath + "/" + id);
    }

    public Response getAllVideoGames() {
        return given()
                .spec(ApiRequestSpecification.getDefaultRequestSpec())
                .get(basePath);
    }

    public Response updateVideoGame(int id, VideoGameRequest request) {
        return given()
                .spec(ApiRequestSpecification.getAuthenticatedRequestSpec())
                .body(request)
                .put(basePath + "/" + id);
    }

    public Response deleteVideoGame(int id) {
        return given()
                .spec(ApiRequestSpecification.getAuthenticatedRequestSpec())
                .delete(basePath + "/" + id);
    }
}