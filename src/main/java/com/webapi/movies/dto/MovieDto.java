package com.webapi.movies.dto;

public class MovieDto {
    private Long id;
    private String title;
    private int year;
    private float rating;

    public MovieDto() {
    }

    public MovieDto(Long id, String title, int year, float rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
