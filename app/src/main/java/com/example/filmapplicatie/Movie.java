package com.example.filmapplicatie;

public class Movie{

    private String popularity;
    private String vote_count;
    private String image;
    private String identificationNumber;
    private String language;
    private String title;
    private String vote_average;
    private String overview;
    private String release_date;
    private String genre;
    private String adult;


    public Movie(String popularity, String vote_count, String image, String identificationNumber, String language, String title, String vote_average, String overview, String release_date, String genre, String adult) {
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.image = image;
        this.identificationNumber = identificationNumber;
        this.language = language;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
        this.genre = genre;
        this.adult = adult;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String getImage() {
        return image;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getGenre() {
        return genre;
    }

    public String getAdult() {
        return adult;
    }
}
