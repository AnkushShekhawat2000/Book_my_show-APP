package com.application.demo.book_my_show.service;

import com.application.demo.book_my_show.converters.MovieConvertor;
import com.application.demo.book_my_show.converters.UserConvertor;
import com.application.demo.book_my_show.entity.MovieEntity;
import com.application.demo.book_my_show.entity.UserEntity;
import com.application.demo.book_my_show.enums.Language;
import com.application.demo.book_my_show.repository.MovieRepository;
import com.application.demo.book_my_show.repository.UserRepository;
import com.application.demo.book_my_show.requestdtos.MovieRequestDto;
import com.application.demo.book_my_show.requestdtos.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto) throws Exception{
        MovieEntity movieEntity = MovieConvertor.convertMovieRequestDtoToMovieEntity(movieRequestDto);
        movieRepository.save(movieEntity);
        return "Movie Added successfully";
    }




    public MovieEntity getMovieByMovieName(String movieName) throws Exception{
        MovieEntity movieEntity = movieRepository.findByMovieName(movieName);
        return movieEntity;
    }


    public List<MovieEntity> getMovieByLanguage(Language language) throws Exception {
        List<MovieEntity> movieEntityList = movieRepository.findByLanguage(language);
        return movieEntityList;
    }
}
