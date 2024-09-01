package com.application.demo.book_my_show.controller;


import com.application.demo.book_my_show.entity.TicketEntity;
import com.application.demo.book_my_show.requestdtos.TicketRequestDto;
import com.application.demo.book_my_show.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {


    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity bookTicket(@RequestBody TicketRequestDto ticketRequestDto){
        try{
            String response = ticketService.addTicket(ticketRequestDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch(Exception e){
            String msg = "Ticket could not be booked";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getticketslessthan500price")
    public ResponseEntity getTicketsByPriceLessThan500(){
        try{
            List<TicketEntity> response = ticketService.getTicketsByPriceLessThan500();
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch(Exception e){
            String msg = "Ticket could not be found!!!";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }


}
