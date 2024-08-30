package com.application.demo.book_my_show.controller;

import com.application.demo.book_my_show.requestdtos.UserRequestDto;
import com.application.demo.book_my_show.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto userRequestDto){
        try{
             String response =  userService.addUser(userRequestDto);
             return new ResponseEntity<>(response, HttpStatus.CREATED );
        }
        catch(Exception e){
            String msg = "User could not be added: "+e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }
}
