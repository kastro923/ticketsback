package com.tekup.ticketsproject.Controllers;

import java.util.List;
import java.util.stream.Collectors;


import com.tekup.ticketsproject.Entities.Enum.ROLES;
import com.tekup.ticketsproject.Entities.User;
import com.tekup.ticketsproject.Repositories.UserRepository;
import com.tekup.ticketsproject.Services.UserDetailsImpl;
import com.tekup.ticketsproject.payload.request.LoginRequest;
import com.tekup.ticketsproject.payload.request.SignUpRequest;
import com.tekup.ticketsproject.payload.response.MessageResponse;
import com.tekup.ticketsproject.payload.response.UserInfoResponse;
import com.tekup.ticketsproject.security.jwt.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;



    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles.get(0),jwtCookie.getValue()));
    }

 /*   @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByName(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByMail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new user's account
        User user = new User();
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getUsername());
        user.setMail(signUpRequest.getEmail());
        user.setEnabled(true);
        user.setRole(ROLES.valueOf(signUpRequest.getRole()));

        System.out.println("$roles*****************************************************");
        System.out.println(user.getRole());
        System.out.println(user.getMail());
        System.out.println(user.getId());
        System.out.println(user.getName());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }*/

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @GetMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(){
        User user = new User();
        user.setMail("admin@admin.com");
        user.setPassword(encoder.encode("admin123"));
        user.setName("admin");
        user.setEnabled(true);
        user.setRole(ROLES.ADMIN);
        user = this.userRepository.save(user);
        return ResponseEntity.ok(user);

    }
}