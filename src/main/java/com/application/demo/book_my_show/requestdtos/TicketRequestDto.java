package com.application.demo.book_my_show.requestdtos;

import com.application.demo.book_my_show.entity.ShowEntity;
import com.application.demo.book_my_show.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
public class TicketRequestDto {

   private int userId;

   private int showId;

   private List<String> requestSeats = new ArrayList<>();


}
