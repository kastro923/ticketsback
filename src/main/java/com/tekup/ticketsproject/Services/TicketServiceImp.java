package com.tekup.ticketsproject.Services;


import com.tekup.ticketsproject.DTO.TicketsDTO;
import com.tekup.ticketsproject.Entities.Enum.ROLES;
import com.tekup.ticketsproject.Entities.Enum.STATUS;
import com.tekup.ticketsproject.Entities.Tickets;
import com.tekup.ticketsproject.Entities.User;
import com.tekup.ticketsproject.Repositories.TicketsRepository;
import com.tekup.ticketsproject.Repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImp implements TicketService{


    TicketsRepository ticketsRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;


    public TicketServiceImp(TicketsRepository ticketsRepository, UserRepository userRepository ,ModelMapper modelMapper) {
        this.ticketsRepository = ticketsRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TicketsDTO> getTickets(String username) throws Exception {
        User user = this.userRepository.findByNameOrMail(username).orElse(null);
        if (user == null){
            throw new Exception("Invalid JWT");
        }
        switch (user.getRole()){
            case ADMIN:
                return this.ticketsRepository.findAll().stream().map((element) -> modelMapper.map(element, TicketsDTO.class)).collect(Collectors.toList());
            case EMPLOYEE:
                return this.ticketsRepository.findByCreatedBy(user).stream().map((element) -> modelMapper.map(element, TicketsDTO.class)).collect(Collectors.toList());
            case TECHNICIEN:
                return this.ticketsRepository.findByTreatedBy(user).stream().map((element) -> modelMapper.map(element, TicketsDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public TicketsDTO create(TicketsDTO ticketsDTO, String username) throws Exception {
        User user = this.userRepository.findByNameOrMail(username).orElse(null);
        if (user == null){
            throw new Exception("Invalid JWT");
        }
        Tickets tickets = this.modelMapper.map(ticketsDTO, Tickets.class);
        tickets.setCreatedBy(user);
        tickets.setCreated_at(new Date());
        tickets.setStatus(STATUS.OPEN);
        tickets = this.ticketsRepository.save(tickets);
        return this.modelMapper.map(tickets, TicketsDTO.class);
    }


    public void deleteTicket(Long id){
        this.ticketsRepository.deleteById(id);
    }

    public TicketsDTO editTicket(TicketsDTO ticketsDTO, long id) throws Exception{
        Tickets ticket = this.ticketsRepository.findById(id).orElse(null);
        if (ticket == null){
            throw new Exception("Ticket not found");
        }
        if(StringUtils.isNotEmpty(ticketsDTO.getTitle())){
            ticket.setTitle(ticketsDTO.getTitle());
        }

        if(StringUtils.isNotEmpty(ticketsDTO.getDescription())){
            ticket.setDescription(ticketsDTO.getDescription());
        }
        if (StringUtils.isNotEmpty(ticketsDTO.getStatus())){
            ticket.setStatus(STATUS.valueOf(ticketsDTO.getStatus()));
        }

        ticket = this.ticketsRepository.save(ticket);
        return this.modelMapper.map(ticket,TicketsDTO.class);
    }
}
