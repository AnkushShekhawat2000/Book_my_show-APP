package com.application.demo.book_my_show.requestdtos;


import com.application.demo.book_my_show.enums.ShowType;
import lombok.Data;

import java.util.Date;

@Data
public class ShowRequestDto {

    private Date showDateTime;

    private ShowType showType;

    private int  movie_id;

    private int theater_id;

    private double classicSeatPrice;

    private double premiumSeatPrice;

}
