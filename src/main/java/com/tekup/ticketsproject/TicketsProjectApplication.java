package com.tekup.ticketsproject;


import com.tekup.ticketsproject.DTO.ERole;
import com.tekup.ticketsproject.DTO.Role;
import com.tekup.ticketsproject.Entities.Enum.ROLES;
import com.tekup.ticketsproject.Entities.User;
import com.tekup.ticketsproject.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EntityScan(basePackages = "com.tekup.ticketsproject.Entities")
public class TicketsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketsProjectApplication.class, args);

    }

 /*   @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {

            User user = new User();
            user.setEnabled(true);
            user.setMail("admin@admin.com");
            user.setName("admin");
            user.setRole(ROLES.ADMIN);
            user.setPassword(encoder.encode("admin123"));

            userRepository.save(user);

        };
    }*/

}
