package com.webapi.movies.service;

import com.webapi.movies.dto.MovieDto;
import com.webapi.movies.dto.MoviePaged;
import com.webapi.movies.exception.MovieNotFoundException;
import com.webapi.movies.mapper.MovieMapper;
import com.webapi.movies.entity.Movie;
import com.webapi.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public ResponseEntity<MoviePaged> getAllMoviesPaged(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Movie> page = movieRepository.findAll(pageable);

        List<MovieDto> movieDtoList = new ArrayList<>();

        page.getContent().forEach((movie) -> movieDtoList.add(MovieMapper.mapToMovieDto(movie)));

        MoviePaged moviePaged = new MoviePaged();

        moviePaged.setContent(movieDtoList);
        moviePaged.setPageNumber(page.getNumber());
        moviePaged.setPageSize(page.getSize());
        moviePaged.setTotalElements(page.getTotalElements());
        moviePaged.setTotalPages(page.getTotalPages());
        moviePaged.setLast(page.isLast());

        return new ResponseEntity<>(moviePaged, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        List<MovieDto> moviesDto = new ArrayList<>();

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
            throw new MovieNotFoundException("Movie with id: " + movieId + " not found.");
        }
    }

    @Override
    public ResponseEntity<MovieDto> createMovie(MovieDto movieDto) {
        Movie movie = MovieMapper.mapToMovie(movieDto);

        Movie movie1 = new Movie();
        movie1.setTitle(movie.getTitle());
        movie1.setYear(movie.getYear());
        movie1.setRating(movie.getRating());

        Movie savedMovie = movieRepository.save(movie1);

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
            throw new MovieNotFoundException("Movie with id: " + movieId + " not found.");
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteMovieById(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        if(movie.isPresent()){
            movieRepository.deleteById(movieId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            throw new MovieNotFoundException("Movie with id: " + movieId + " not found.");
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAllMovies() {
        movieRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
