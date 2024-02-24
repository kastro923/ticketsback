package com.tekup.ticketsproject.Repositories;

import java.util.Optional;


import com.tekup.ticketsproject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name = ?1 or u.mail = ?1")
    Optional<User> findByNameOrMail(String username);

    Boolean existsByName(String username);

    Boolean existsByMail(String email);
}