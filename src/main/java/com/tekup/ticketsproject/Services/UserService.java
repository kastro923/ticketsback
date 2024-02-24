package com.tekup.ticketsproject.Services;

import com.tekup.ticketsproject.DTO.UserDTO;
import com.tekup.ticketsproject.payload.request.SignUpRequest;

import java.util.List;

public interface UserService {
    public List<UserDTO> listUser();
    public UserDTO createUser(SignUpRequest signUpRequest) throws Exception;
    public void deleteUser(Long id) throws Exception;
    void updateUser(SignUpRequest user) throws Exception;
}
