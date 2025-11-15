package com.cfs.BookMyShow.repository;

import com.cfs.BookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existingByEmail(String email);

}
