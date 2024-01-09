package com.webapi.movies.service;

import com.webapi.movies.dto.MovieDto;
import com.webapi.movies.dto.MoviePaged;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovieService {
    ResponseEntity<MoviePaged> getAllMoviesPaged(int pageNumber, int pageSize);
    ResponseEntity<List<MovieDto>> getAllMovies();
    ResponseEntity<MovieDto> getMovieById(Long movieId);
    ResponseEntity<MovieDto> createMovie(MovieDto movieDto);
    ResponseEntity<MovieDto> updateMovie(Long movieId, MovieDto movieDto);
    ResponseEntity<HttpStatus> deleteMovieById(Long movieId);
    ResponseEntity<HttpStatus> deleteAllMovies();
}
