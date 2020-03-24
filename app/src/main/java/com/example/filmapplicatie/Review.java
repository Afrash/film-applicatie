package com.example.filmapplicatie;

public class Review {

    private String reviewId;
    private int ratingAverage;
    private int numberOfVotes;

    public Review(String reviewId, int ratingAverage, int numberOfVotes) {
        this.reviewId = reviewId;
        this.ratingAverage = ratingAverage;
        this.numberOfVotes = numberOfVotes;
    }

    public String getReviewId() {
        return reviewId;
    }

    public int getRatingAverage() {
        return ratingAverage;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setRatingAverage(int ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
