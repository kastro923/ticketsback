package com.tekup.ticketsproject.Controllers;

import com.tekup.ticketsproject.DTO.UserDTO;
import com.tekup.ticketsproject.Entities.Enum.ROLES;
import com.tekup.ticketsproject.Entities.User;
import com.tekup.ticketsproject.Repositories.UserRepository;
import com.tekup.ticketsproject.Services.UserService;
import com.tekup.ticketsproject.payload.request.SignUpRequest;
import com.tekup.ticketsproject.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsers(){

        return new ResponseEntity<>(this.userService.listUser(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {

        try{
            return new ResponseEntity<>(this.userService.createUser(signUpRequest),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            this.userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> editUser(@RequestBody SignUpRequest user, @PathVariable("id") long id){
        try{
            this.userService.updateUser(user,id);
            return new ResponseEntity<>(new MessageResponse("User updated successfully"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/techList")
    public ResponseEntity<?> techList(){
        return new ResponseEntity<>(this.userService.techList(),HttpStatus.OK);
    }
}
