package com.Ejada.SpringTask.Application.Services;

import com.Ejada.SpringTask.Application.Models.User;

import java.util.List;

public interface UserServices {
    List<User> getAllUsers();

    void addUser(User user);

    User getUserbyId(int id);

    void deleteUserById(int id);

    void updateUserById(int userId, User user);

//    User findByName(String userName);
}
