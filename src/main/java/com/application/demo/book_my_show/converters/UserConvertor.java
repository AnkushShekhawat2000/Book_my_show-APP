package com.application.demo.book_my_show.converters;

import com.application.demo.book_my_show.entity.UserEntity;
import com.application.demo.book_my_show.requestdtos.UserRequestDto;

public class UserConvertor {


    // without creating object we can call directly
    public static UserEntity convertUserRequestDtoToUserEntity(UserRequestDto userRequestDto){
        UserEntity userEntity = UserEntity.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .email(userRequestDto.getEmail())
                .mobile(userRequestDto.getMobile())
                .address(userRequestDto.getAddress())
                .build();

        return userEntity;
    }
}
