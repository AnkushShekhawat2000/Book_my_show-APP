package com.application.demo.book_my_show.converters;

import com.application.demo.book_my_show.entity.ShowEntity;
import com.application.demo.book_my_show.entity.UserEntity;
import com.application.demo.book_my_show.requestdtos.ShowRequestDto;
import com.application.demo.book_my_show.requestdtos.UserRequestDto;

public class ShowConvertor {

    // without creating object we can call directly
    public static ShowEntity convertShowRequestDtoToShowrEntity(ShowRequestDto showRequestDto){
        ShowEntity showEntity = ShowEntity.builder()
                .showDateTime(showRequestDto.getShowDateTime())
                .showType(showRequestDto.getShowType())
                .build();

        return showEntity;
    }
}
