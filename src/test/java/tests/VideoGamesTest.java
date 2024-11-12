package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import models.VideoGame;
import models.VideoGameRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import services.VideoGameService;
import utils.ReportLogger;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Video Game API Tests")
@Feature("Video Game CRUD Operations")
@TestMethodOrder(OrderAnnotation.class)
public class VideoGamesTest extends BaseTest {
    private final VideoGameService videoGameService;
    private static Integer createdGameId;

    public VideoGamesTest() {
        this.videoGameService = new VideoGameService();
    }

    @Test
    @Order(1)
    @DisplayName("Create New Video Game")
    @Description("Verify that a new video game can be created successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void createVideoGame() {
        try {
            ReportLogger.startTest("Create New Video Game");

            // Arrange
            VideoGameRequest request = new VideoGameRequest.Builder()
                    .withName("Super Mario Odyssey")
                    .withReleaseDate("2017-10-27")
                    .withReviewScore(97)
                    .withCategory("Platform")
                    .withRating("E")
                    .build();

            // Act
            Response response = videoGameService.createVideoGame(request);
            ReportLogger.logResponse(response.getStatusCode(), response.getBody().asString());

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(200);
            VideoGame createdGame = response.as(VideoGame.class);
            createdGameId = createdGame.getId();

            assertThat(createdGame.getName()).isEqualTo(request.getName());
            assertThat(createdGame.getCategory()).isEqualTo(request.getCategory());

            ReportLogger.info("Successfully created new video game");
        } catch (Exception e) {
            ReportLogger.error("Test failed", e);
            throw e;
        }
    }

    @Test
    @Order(2)
    @DisplayName("Update Video Game")
    @Description("Verify that an existing video game can be updated successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void updateVideoGame() {
        Assumptions.assumeTrue(createdGameId != null, "Game ID should not be null");

        try {
            ReportLogger.startTest("Update Video Game");

            // Arrange
            VideoGameRequest updatedRequest = new VideoGameRequest.Builder()
                    .withName("Super Mario Odyssey Updated")
                    .withReleaseDate("2017-10-27")
                    .withReviewScore(98)
                    .withCategory("Platform")
                    .withRating("E")
                    .build();

            // Act
            Response response = videoGameService.updateVideoGame(createdGameId, updatedRequest);
            ReportLogger.logResponse(response.getStatusCode(), response.getBody().asString());

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(200);
            VideoGame updatedGame = response.as(VideoGame.class);

            assertThat(updatedGame.getName()).isEqualTo(updatedRequest.getName());
            assertThat(updatedGame.getReviewScore()).isEqualTo(updatedRequest.getReviewScore());

            ReportLogger.info("Successfully updated video game");
        } catch (Exception e) {
            ReportLogger.error("Test failed", e);
            throw e;
        }
    }

    @Test
    @Order(3)
    @DisplayName("Get Video Game By ID")
    @Description("Verify that a specific video game can be retrieved by ID")
    @Severity(SeverityLevel.CRITICAL)
    public void getVideoGameById() {
        Assumptions.assumeTrue(createdGameId != null, "Game ID should not be null");

        try {
            ReportLogger.startTest("Get Video Game By ID");

            // Act
            Response response = videoGameService.getVideoGameById(createdGameId);
            ReportLogger.logResponse(response.getStatusCode(), response.getBody().asString());

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(200);
            VideoGame game = response.as(VideoGame.class);
            assertThat(game.getId()).isEqualTo(createdGameId);

            ReportLogger.info("Successfully retrieved video game by ID");
        } catch (Exception e) {
            ReportLogger.error("Test failed", e);
            throw e;
        }
    }

    @Test
    @Order(4)
    @DisplayName("Get All Video Games")
    @Description("Verify that all video games can be retrieved successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void getAllVideoGames() {
        try {
            ReportLogger.startTest("Get All Video Games");

            // Act
            Response response = videoGameService.getAllVideoGames();
            ReportLogger.logResponse(response.getStatusCode(), response.getBody().asString());

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(200);
            assertThat(response.getContentType()).containsIgnoringCase("application/json");
            assertThat(response.jsonPath().getList("$")).isNotEmpty();

            ReportLogger.info("Successfully retrieved all video games");
        } catch (Exception e) {
            ReportLogger.error("Test failed", e);
            throw e;
        }
    }

    @Test
    @Order(5)
    @DisplayName("Delete Video Game")
    @Description("Verify that a video game can be deleted successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteVideoGame() {
        Assumptions.assumeTrue(createdGameId != null, "Game ID should not be null");

        try {
            ReportLogger.startTest("Delete Video Game");

            // Act
            Response response = videoGameService.deleteVideoGame(createdGameId);
            ReportLogger.logResponse(response.getStatusCode(), response.getBody().asString());

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(200);

            // Verify deletion
            Response getResponse = videoGameService.getVideoGameById(createdGameId);
            assertThat(getResponse.getStatusCode()).isEqualTo(404);

            ReportLogger.info("Successfully deleted video game");
        } catch (Exception e) {
            ReportLogger.error("Test failed", e);
            throw e;
        }
    }
}