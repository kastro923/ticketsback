package com.tekup.ticketsproject.Repositories;


import com.tekup.ticketsproject.Entities.Enum.STATUS;
import com.tekup.ticketsproject.Entities.Tickets;
import com.tekup.ticketsproject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

 @Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {

     List<Tickets> findByCreatedBy(User user);
     List<Tickets> findByTreatedBy(User user);
}