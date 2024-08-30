package com.application.demo.book_my_show.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;

    private Date showDateTime;

    private double totalAmount;

    private String bookedSeats;

    private String theaterName;

    private String ticketId = UUID.randomUUID().toString();

    @JoinColumn
    @ManyToOne
    private UserEntity userEntity;

    @JoinColumn
    @ManyToOne
    private ShowEntity showEntity;

}
