// src/main/java/models/VideoGameRequest.java
package models;

public class VideoGameRequest {
    private String name;
    private String releaseDate;
    private Integer reviewScore;
    private String category;
    private String rating;

    // Builder pattern for easy object creation
    public static class Builder {
        private VideoGameRequest request;

        public Builder() {
            request = new VideoGameRequest();
        }

        public Builder withName(String name) {
            request.name = name;
            return this;
        }

        public Builder withReleaseDate(String releaseDate) {
            request.releaseDate = releaseDate;
            return this;
        }

        public Builder withReviewScore(Integer reviewScore) {
            request.reviewScore = reviewScore;
            return this;
        }

        public Builder withCategory(String category) {
            request.category = category;
            return this;
        }

        public Builder withRating(String rating) {
            request.rating = rating;
            return this;
        }

        public VideoGameRequest build() {
            return request;
        }
    }

    // Getters and Setters
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
}
