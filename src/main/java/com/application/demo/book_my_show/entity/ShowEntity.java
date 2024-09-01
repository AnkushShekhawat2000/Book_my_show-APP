package com.application.demo.book_my_show.entity;


import com.application.demo.book_my_show.enums.ShowType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="shows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date showDateTime;

    private Date createdOn;

    private Date updatedOn;


    @Enumerated(value=EnumType.STRING)
    private ShowType showType;

    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<TicketEntity> listOFBookedTickets = new ArrayList<>();


    @JoinColumn
    @ManyToOne
    private MovieEntity movieEntity;

    @JoinColumn
    @ManyToOne
    private TheaterEntity theaterEntity;

    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfShowSeat = new ArrayList<>();




}
