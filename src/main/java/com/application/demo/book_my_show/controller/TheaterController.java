package com.application.demo.book_my_show.controller;


import com.application.demo.book_my_show.requestdtos.TheaterRequestDto;
import com.application.demo.book_my_show.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<?> addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        try{
            String response =  theaterService.addTheater(theaterRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED );
        }
        catch(Exception e){
            String msg = "Theater could not be added: "+e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }
}
