package com.webapi.movies.controller;

import com.webapi.movies.dto.MovieDto;
import com.webapi.movies.service.infc.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    @Autowired
    private IMovieService iMovieService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return iMovieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long movieId){
        return iMovieService.getMovieById(movieId);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){
        return iMovieService.createMovie(movieDto);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long movieId, @RequestBody MovieDto movieDto){
        return iMovieService.updateMovie(movieId, movieDto);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<List<MovieDto>> deleteMovieById(@PathVariable("id") Long movieId){
        return iMovieService.deleteMovieById(movieId);
    }

    @DeleteMapping("/movies")
    public ResponseEntity<HttpStatus> deleteAllMovies(){
        return iMovieService.deleteAllMovies();
    }
}
