package com.Ejada.SpringTask.application.repositories;

import com.Ejada.SpringTask.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrUserName(String email, String userName);
}

