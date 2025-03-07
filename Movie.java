package com.trailerHunt;

public class Movie {
    private int id;
    private String title;
    private String cast;
    private String genre;
    private int year;
    private double rating;
    private String trailerUrl;
    private String poster; 

    public Movie(int id, String title, String cast, String genre, int year, double rating, String trailerUrl, String poster) {
        this.id = id;
        this.title = title;
        this.cast = cast;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.trailerUrl = trailerUrl;
        this.poster = poster; 
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCast() { return cast; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public double getRating() { return rating; }
    public String getTrailerUrl() { return trailerUrl; }
    public String getPoster() { return poster; } 
}
