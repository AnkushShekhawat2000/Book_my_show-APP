package com.application.demo.book_my_show.requestdtos;

import com.application.demo.book_my_show.enums.Genre;
import com.application.demo.book_my_show.enums.Language;
import lombok.Data;

@Data
public class MovieRequestDto {

    private String movieName;

    private double rating;

    private int duration;

    private Language language;

    private Genre genre;
}
