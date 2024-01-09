package com.webapi.movies.mapper;

import com.webapi.movies.dto.MovieDto;
import com.webapi.movies.entity.Movie;

public class MovieMapper {
    public static MovieDto mapToMovieDto(Movie movie){
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getRating()
        );
    }

    public static Movie mapToMovie(MovieDto movieDto){
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getYear(),
                movieDto.getRating()
        );
    }
}
