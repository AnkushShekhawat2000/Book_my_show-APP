package com.application.demo.book_my_show.entity;

import com.application.demo.book_my_show.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="theaterSeat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheaterSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value=EnumType.STRING)
    private SeatType seatType;

    private String seatNo;

    @JoinColumn
    @ManyToOne
//    @JsonBackReference
    private TheaterEntity theaterEntity;
}
