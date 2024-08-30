package com.application.demo.book_my_show.converters;

import com.application.demo.book_my_show.entity.TicketEntity;
import com.application.demo.book_my_show.entity.UserEntity;
import com.application.demo.book_my_show.requestdtos.TicketRequestDto;
import com.application.demo.book_my_show.requestdtos.UserRequestDto;

public class TicketConvertor {

    // without creating object we can call directly
    public static TicketEntity convertTicketRequestDtoToTicketEntity(TicketRequestDto ticketRequestDto){
        TicketEntity ticketEntity = new TicketEntity();
        return ticketEntity;

    }

}
