package com.application.demo.book_my_show.service;


import com.application.demo.book_my_show.converters.TheaterConvertor;
import com.application.demo.book_my_show.entity.TheaterEntity;
import com.application.demo.book_my_show.entity.TheaterSeatEntity;
import com.application.demo.book_my_show.enums.SeatType;
import com.application.demo.book_my_show.repository.TheaterRepository;
import com.application.demo.book_my_show.repository.TheaterSeatRepository;
import com.application.demo.book_my_show.requestdtos.TheaterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterRequestDto theaterRequestDto)throws Exception {
        // do some empty/blank validations
        if (theaterRequestDto.getName() == null || theaterRequestDto.getLocation() == null) {
            throw new Exception("Name and location should valid");
        }

        TheaterEntity theaterEntity = TheaterConvertor.convertTheaterRequestDtoToTheaterEntity(theaterRequestDto);
        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterRequestDto, theaterEntity);
        theaterEntity.setListOfTheaterSeats(theaterSeatEntityList);
        theaterRepository.save(theaterEntity);
        return "Theater Added successfully";

    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterRequestDto theaterRequestDto, TheaterEntity theaterEntity) {

            int noClassicSeats = theaterRequestDto.getClassicSeatCount();
            int noPremiumSeats = theaterRequestDto.getPremiumSeatsCount();
            List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();


            // create the classic seats
            for (int count = 1; count <= noClassicSeats; count++) {
                TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                        .seatType(SeatType.CLASSIC)
                        .seatNo("C" + count)         // c1 c2 c3 ....
                        .theaterEntity(theaterEntity)
                        .build();
                theaterSeatEntityList.add(theaterSeatEntity);
            }


            // create the premium seats
            for (int count = 1; count <= noPremiumSeats; count++) {
                TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                        .seatType(SeatType.PREMIUM)
                        .seatNo("P" + count)
                        .theaterEntity(theaterEntity)
                        .build();
                theaterSeatEntityList.add(theaterSeatEntity);
            }


            return theaterSeatEntityList;
    }

}