package com.application.demo.book_my_show.entity;


import com.application.demo.book_my_show.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="show-seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;

    private int price;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private String seatNo;

    private Date bookedAt;

    @JoinColumn
    @ManyToOne
    private ShowEntity showEntity;
}
