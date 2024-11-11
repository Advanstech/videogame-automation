// src/main/java/models/VideoGame.java
package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoGame {
    private Integer id;
    private String name;
    private String releaseDate;
    private Integer reviewScore;
    private String category;
    private String rating;

    // Default constructor
    public VideoGame() {}

    // Constructor with all fields
    public VideoGame(Integer id, String name, String releaseDate,
                     Integer reviewScore, String category, String rating) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
        this.category = category;
        this.rating = rating;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public Integer getReviewScore() { return reviewScore; }
    public void setReviewScore(Integer reviewScore) { this.reviewScore = reviewScore; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    // ToString method for better logging
    @Override
    public String toString() {
    }
}
