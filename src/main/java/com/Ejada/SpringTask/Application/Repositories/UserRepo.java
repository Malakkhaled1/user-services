package com.Ejada.SpringTask.Application.Repositories;

import com.Ejada.SpringTask.Application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByName(String name);
}
