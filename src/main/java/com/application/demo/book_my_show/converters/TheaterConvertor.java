package com.application.demo.book_my_show.converters;

import com.application.demo.book_my_show.entity.TheaterEntity;
import com.application.demo.book_my_show.entity.UserEntity;
import com.application.demo.book_my_show.requestdtos.TheaterRequestDto;
import com.application.demo.book_my_show.requestdtos.UserRequestDto;

public class TheaterConvertor {

    // without creating object we can call directly
    public static TheaterEntity convertTheaterRequestDtoToTheaterEntity(TheaterRequestDto theaterRequestDto){
        TheaterEntity theaterEntity = TheaterEntity.builder()
                .name(theaterRequestDto.getName())
                .location(theaterRequestDto.getLocation())
                .build();

        return theaterEntity;
    }
}
