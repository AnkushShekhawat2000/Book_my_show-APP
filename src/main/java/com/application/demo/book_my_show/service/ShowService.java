package com.application.demo.book_my_show.service;


import com.application.demo.book_my_show.converters.ShowConvertor;
import com.application.demo.book_my_show.entity.*;
import com.application.demo.book_my_show.enums.SeatType;
import com.application.demo.book_my_show.repository.MovieRepository;
import com.application.demo.book_my_show.repository.ShowRepository;
import com.application.demo.book_my_show.repository.TheaterRepository;
import com.application.demo.book_my_show.requestdtos.ShowRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowService {


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;


    @Autowired
    private ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){

        ShowEntity showEntity = ShowConvertor.convertShowRequestDtoToShowrEntity(showRequestDto);

        int movieId = showRequestDto.getMovie_id();
        int theaterId = showRequestDto.getTheater_id();

        // set the movie and theater details with movieId and theaterID
        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();

        showEntity.setMovieEntity(movieEntity);  // which movies is displayed in show
        showEntity.setTheaterEntity(theaterEntity);  // which theater that show is put on

        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showRequestDto, showEntity);
        showEntity.setListOfShowSeat(showSeatEntityList);   //seat details are also added into the show

        showEntity.setShowDateTime(showRequestDto.getShowDateTime());
        showEntity.setCreatedOn(new Date());
        showEntity.setUpdatedOn(new Date());

        showEntity = showRepository.save(showEntity);

        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);

        return "Show has been successfully added";
    }







     private List<ShowSeatEntity> createShowSeatEntity(ShowRequestDto showRequestDto, ShowEntity showEntity){

         // create the showEntity and set it attribute or fields
         TheaterEntity theaterEntity = showEntity.getTheaterEntity();

         List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getListOfTheaterSeats();

         List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

         // for each theaterseat a corresponding showseat is creating
         for(TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList){
             ShowSeatEntity showSeatEntity = new ShowSeatEntity();
             showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
             showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

             if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                 showSeatEntity.setPrice(showRequestDto.getClassicSeatPrice());
             }else{
                 showSeatEntity.setPrice(showRequestDto.getPremiumSeatPrice());
             }

             showSeatEntity.setBooked(false);
             showSeatEntity.setShowEntity(showEntity);

             showSeatEntityList.add(showSeatEntity);
         }

         return showSeatEntityList;

     }


}
