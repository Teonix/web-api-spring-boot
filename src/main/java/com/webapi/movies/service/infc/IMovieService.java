package com.webapi.movies.service.infc;

import com.webapi.movies.dto.MovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovieService {
    ResponseEntity<List<MovieDto>> getAllMovies();
    ResponseEntity<MovieDto> getMovieById(Long movieId);
    ResponseEntity<MovieDto> createMovie(MovieDto movieDto);
    ResponseEntity<MovieDto> updateMovie(Long movieId, MovieDto movieDto);
    ResponseEntity<List<MovieDto>> deleteMovieById(Long movieId);
    ResponseEntity<HttpStatus> deleteAllMovies();
}
