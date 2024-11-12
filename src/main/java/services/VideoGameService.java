package services;

import io.restassured.response.Response;
import models.VideoGameRequest;
import utils.ConfigLoader;
import utils.ApiRequestSpecification;

import static io.restassured.RestAssured.given;

public class VideoGameService {
    private static final ConfigLoader  config = ConfigLoader.getInstance();
    private final String basePath = config.getProperty("api.base.url") + config.getProperty("api.endpoint.videogame");

    public VideoGameService() {
        String baseUrl = ConfigLoader.getInstance().getProperty("api.base.url");
        System.out.println("VideoGameService Base URL: " + baseUrl);
        System.out.println("VideoGameService Base Path: " + basePath);
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