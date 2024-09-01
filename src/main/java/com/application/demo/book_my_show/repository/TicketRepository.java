package com.application.demo.book_my_show.repository;

import com.application.demo.book_my_show.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    // native querry - customization of user querry


    @Query(nativeQuery = true, value = "select * from tickets where total_amount<500")
    public List<TicketEntity> getTicketsByPriceLessThan500();
}
