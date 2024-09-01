package com.application.demo.book_my_show.controller;

import com.application.demo.book_my_show.requestdtos.ShowRequestDto;
import com.application.demo.book_my_show.service.ShowService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public ResponseEntity addShow(@RequestBody ShowRequestDto showRequestDto){
        try{
            String response = showService.addShow(showRequestDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch(Exception e){
            String msg ="Show could not be added : "+e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

        }
    }
}
