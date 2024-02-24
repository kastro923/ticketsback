package com.tekup.ticketsproject.Config;

import com.tekup.ticketsproject.DTO.ERole;
import com.tekup.ticketsproject.DTO.TicketsDTO;
import com.tekup.ticketsproject.DTO.UserDTO;
import com.tekup.ticketsproject.Entities.Tickets;
import com.tekup.ticketsproject.Entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Tickets.class, TicketsDTO.class)
                .addMapping(src -> src.getCreatedBy().getName(), TicketsDTO::setCreatedBy)
                .addMapping(Tickets::getStatus, TicketsDTO::setStatus);
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMapping(UserDTO::getEmail,User::setMail)
                .addMapping(UserDTO::getUsername,User::setName);
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMapping(User::getName, UserDTO::setUsername)
                .addMapping(User::getMail, UserDTO::setEmail)
                .addMapping(User::getRole,UserDTO::setRole);


        return modelMapper;
    }
}