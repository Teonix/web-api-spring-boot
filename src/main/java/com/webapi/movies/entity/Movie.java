package com.webapi.movies.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "year")
    private int year;
    @Column(name = "rating")
    private float rating;

    public Long getId() {
        return id;
    }

    public Movie() {
    }

    public Movie(Long id, String title, int year, float rating) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
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
