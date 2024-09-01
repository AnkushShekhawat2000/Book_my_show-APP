package com.application.demo.book_my_show.service;


import com.application.demo.book_my_show.converters.TicketConvertor;
import com.application.demo.book_my_show.entity.ShowEntity;
import com.application.demo.book_my_show.entity.ShowSeatEntity;
import com.application.demo.book_my_show.entity.TicketEntity;
import com.application.demo.book_my_show.entity.UserEntity;
import com.application.demo.book_my_show.repository.ShowRepository;
import com.application.demo.book_my_show.repository.TicketRepository;
import com.application.demo.book_my_show.repository.UserRepository;
import com.application.demo.book_my_show.requestdtos.TheaterRequestDto;
import com.application.demo.book_my_show.requestdtos.TicketRequestDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public String addTicket(TicketRequestDto ticketRequestDto) throws Exception{

        // convert the requestdto into entity
        TicketEntity ticketEntity = TicketConvertor.convertTicketRequestDtoToTicketEntity(new TicketRequestDto());

        // validation - check whether requested seats are  available or not
        boolean isSeatsAvailable =checkRequestedSeatsAvailabilty(ticketRequestDto);
        if(isSeatsAvailable  == false){
            throw new Exception("Requested seats are not available, please select other seats");
        }

        // calculate the total amount of the booking
        ShowEntity showEntity = showRepository.findById(ticketRequestDto.getShowId()).get();
        List<ShowSeatEntity> showSeatEntityList = showEntity.getListOfShowSeat();

        List<String> requestedSeats = ticketRequestDto.getRequestSeats(); // which seats user are selecting

        double totalAmount = 0.00;
        for(ShowSeatEntity showSeatEntity : showSeatEntityList ){
            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
                totalAmount = totalAmount + showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }

        }

        ticketEntity.setTotalAmount(totalAmount);
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDateTime(showEntity.getShowDateTime());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());

        String allottedSeats = getAllottedSeats(requestedSeats);
        ticketEntity.setBookedSeats(allottedSeats);

        ticketEntity.setShowEntity(showEntity);

        UserEntity userEntity = userRepository.findById(ticketRequestDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);

        ticketEntity = ticketRepository.save(ticketEntity);

        List<TicketEntity> ticketEntityList = showEntity.getListOFBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOFBookedTickets(ticketEntityList);

        showRepository.save(showEntity);


        List<TicketEntity> ticketEntityList1 = userEntity.getBookTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookTickets(ticketEntityList1);

        userRepository.save(userEntity);

        String bodyMail = "Hi "+userEntity.getName()+",\n this is to confirm your booking for seat no/s : "+allottedSeats+"fot the movie " +ticketEntity.getMovieName()
                +" and the show timings is : "+ticketEntity.getShowDateTime()+ " and theater is "+ ticketEntity.getTheaterName()+" and your total amount is "+totalAmount;


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("ashekhawat8191@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText(bodyMail);
        mimeMessageHelper.setSubject("Confirming your booked tickets!");
        javaMailSender.send(mimeMessage);


        return "Ticket has been successfully booked";

    }

    private String getAllottedSeats(List<String> requestedSeats){
        String result = "";

        for(String seat : requestedSeats){
            result = result+seat+" ";   //C1, P2, C3, P8
        }
        return result;
    }


    private boolean checkRequestedSeatsAvailabilty(TicketRequestDto ticketRequestDto){

        int showId = ticketRequestDto.getShowId();
        List<String> requestedSeats = ticketRequestDto.getRequestSeats();   // which seats user are selecting

       ShowEntity showEntity = showRepository.findById(showId).get();

       List<ShowSeatEntity>  showSeatEntityList = showEntity.getListOfShowSeat();

       for(ShowSeatEntity showSeatEntity : showSeatEntityList){
         String seatNo =  showSeatEntity.getSeatNo();

         if(requestedSeats.contains(seatNo)){
             if(showSeatEntity.isBooked()==true){
                 return false;   // seat is already booked
             }
         }
       }

       return true;   // seats are available
    }

    public List<TicketEntity> getTicketsByPriceLessThan500(){
        List<TicketEntity> ticketEntityList = ticketRepository.getTicketsByPriceLessThan500();
        return ticketEntityList;
    }
}
