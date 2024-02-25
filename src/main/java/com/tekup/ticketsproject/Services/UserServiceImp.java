package com.tekup.ticketsproject.Services;


import com.tekup.ticketsproject.DTO.UserDTO;
import com.tekup.ticketsproject.Entities.Enum.ROLES;
import com.tekup.ticketsproject.Entities.User;
import com.tekup.ticketsproject.Repositories.UserRepository;
import com.tekup.ticketsproject.payload.request.SignUpRequest;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    UserRepository userRepository;
    ModelMapper modelMapper;
    PasswordEncoder encoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public List<UserDTO> listUser() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map((element) -> modelMapper.map(element, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(SignUpRequest signUpRequest) throws Exception {
        if (userRepository.existsByName(signUpRequest.getUsername())) {
            throw new Exception("Error: Username is already taken!");
        }

        if (userRepository.existsByMail(signUpRequest.getEmail())) {
            throw new Exception("Error: Email is already taken!");
        }
        User user = new User();
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getUsername());
        user.setMail(signUpRequest.getEmail());
        user.setEnabled(true);
        user.setRole(ROLES.valueOf(signUpRequest.getRole()));
        user = userRepository.save(user);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Unable to delete user");
        }
    }

    @Override
    public void updateUser(SignUpRequest user,long id) throws Exception {
        User dbUser = this.userRepository.findById(id).orElse(null);
        if (dbUser == null) {
            throw new Exception("User not found. Invalid mail");
        }
        if (StringUtils.isNotEmpty(user.getPassword())) {
            dbUser.setPassword(this.encoder.encode(user.getPassword()));
        }
        if (StringUtils.isNotEmpty(user.getEmail())) {
            dbUser.setMail(user.getEmail());
        }
        if (StringUtils.isNotEmpty(user.getUsername())) {
            dbUser.setName(user.getUsername());
        }
        this.userRepository.save(dbUser);
    }


    @Override
    public List<UserDTO> techList() {
        var users = this.userRepository.findByRole(ROLES.TECHNICIEN);
        return users.stream().map(x->this.modelMapper.map(x,UserDTO.class)).collect(Collectors.toList());
    }
}
