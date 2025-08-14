package com.Ejada.SpringTask.Application.Services.Impl;

import com.Ejada.SpringTask.Application.Exceptions.ApiRequestExceptions;
import com.Ejada.SpringTask.Application.Models.User;
import com.Ejada.SpringTask.Application.Repositories.UserRepo;
import com.Ejada.SpringTask.Application.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    public void validateUserData(User user) {

        // ID must be positive
        if (user.getId() <= 0) {
            throw new ApiRequestExceptions("User ID must be a positive number.");
        }

        // Password length between 2 and 10
        if (user.getPassword() == null || user.getPassword().length() < 2 || user.getPassword().length() > 10) {
            throw new ApiRequestExceptions("Password length must be between 2 and 10 characters.");
        }

        // Name should not be numeric
        if (user.getName() == null || user.getName().matches("\\d+")) {
            throw new ApiRequestExceptions("Name cannot be a number.");
        }

        // Email should contain @
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new ApiRequestExceptions("Invalid Email address.");
        }
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void addUser(User user){

        // User already exists
        if (userRepo.existsById(user.getId())) {
            throw new ApiRequestExceptions("User with ID " + user.getId() + " already exists.");
        }

        validateUserData(user);
        System.out.println(user);

        userRepo.save(user);
    }

    public User getUserbyId(int userId){
        return userRepo.findById(userId).
                orElseThrow(() -> new ApiRequestExceptions("User with ID " + userId + " not found"));
    }

    public void deleteUserById(int userId){
        if (!userRepo.existsById(userId)) {
            throw new ApiRequestExceptions("User with ID " + userId + " does not exist.");
        }

        userRepo.deleteById(userId);
    }

    public void updateUserById(int userId, User user){
        validateUserData(user);

        if (!userRepo.existsById(userId)) {
            throw new ApiRequestExceptions("User with ID " + userId + " does not exist.");
        }

        if (user.getId() != userId) {
            throw new ApiRequestExceptions("Updating the user ID is not allowed.");
        }

        userRepo.save(user);
    }

    public User findByName(String userName){
        return userRepo.findByName(userName);
    }
}
