package com.webapi.movies.service;

import com.webapi.movies.dto.MovieDto;
import com.webapi.movies.mapper.MovieMapper;
import com.webapi.movies.model.Movie;
import com.webapi.movies.service.infc.IMovieService;
import com.webapi.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        List<MovieDto> moviesDto = new ArrayList<MovieDto>();

        movieRepository.findAll().forEach(movies::add);

        if(movies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        movies.forEach((movie) -> moviesDto.add(MovieMapper.mapToMovieDto(movie)));


        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MovieDto> getMovieById(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        if(movie.isPresent()){
            return new ResponseEntity<>(MovieMapper.mapToMovieDto(movie.get()), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<MovieDto> createMovie(MovieDto movieDto) {
        Movie movie = MovieMapper.mapToMovie(movieDto);
        Movie savedMovie = movieRepository.save(movie);

        return new ResponseEntity<>(MovieMapper.mapToMovieDto(savedMovie), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MovieDto> updateMovie(Long movieId, MovieDto movieDto) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        if(movie.isPresent()){
            movie.get().setTitle(movieDto.getTitle());
            movie.get().setYear(movieDto.getYear());
            movie.get().setRating(movieDto.getRating());
            Movie updatedMovie = movieRepository.save(movie.get());
            return new ResponseEntity<>(MovieMapper.mapToMovieDto(updatedMovie), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<MovieDto>> deleteMovieById(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        if(movie.isPresent()){
            movieRepository.deleteById(movieId);
            return getAllMovies();
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAllMovies() {
        movieRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
