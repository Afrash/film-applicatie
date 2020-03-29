package com.example.filmapplicatie.review;

public class Review {

    private String movie;
    private String review;
    private String rating;

    public Review(){

    }

    public Review(String movie, String review, String rating) {
        this.movie = movie;
        this.review = review;
        this.rating = rating;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMovie() {
        return movie;
    }

    public String getReview() {
        return review;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "movie='" + movie + '\'' +
                ", review='" + review + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
