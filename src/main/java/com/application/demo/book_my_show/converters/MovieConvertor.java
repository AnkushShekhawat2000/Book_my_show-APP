package com.application.demo.book_my_show.converters;

import com.application.demo.book_my_show.entity.MovieEntity;
import com.application.demo.book_my_show.entity.UserEntity;
import com.application.demo.book_my_show.requestdtos.MovieRequestDto;
import com.application.demo.book_my_show.requestdtos.UserRequestDto;

public class MovieConvertor {

    // without creating object we can call directly
    public static MovieEntity convertMovieRequestDtoToMovieEntity(MovieRequestDto movieRequestDto){
        MovieEntity movieEntity = MovieEntity.builder()
                .movieName(movieRequestDto.getMovieName())
                .rating(movieRequestDto.getRating())
                .duration(movieRequestDto.getDuration())
                .language(movieRequestDto.getLanguage())
                .genre(movieRequestDto.getGenre())
                .build();

        return movieEntity;
    }


}
