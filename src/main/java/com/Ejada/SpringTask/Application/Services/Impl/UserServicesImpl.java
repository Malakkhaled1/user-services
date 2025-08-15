package com.Ejada.SpringTask.Application.Services.Impl;

import com.Ejada.SpringTask.Application.Exceptions.ApiRequestExceptions;
import com.Ejada.SpringTask.Application.Models.User;
import com.Ejada.SpringTask.Application.Models.UserCreateRequest;
import com.Ejada.SpringTask.Application.Models.UserRes;
import com.Ejada.SpringTask.Application.Models.UserResList;
import com.Ejada.SpringTask.Application.Repositories.UserRepo;
import com.Ejada.SpringTask.Application.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    public UserResList getAllUsers(){

        List<User> users = userRepo.findAll();
        UserResList userResList = new UserResList();

        List<UserRes> usersList = users.stream()
                .map(UserRes::convertToUserRes)
                .toList();

        userResList.setUserResList(usersList);

        return userResList;
    }

    public UserRes addUser(UserCreateRequest request){
        Optional<User> existingUser = userRepo.findByEmailOrUserName(
                request.getEmail(),
                request.getUserName()
        );

        if (existingUser.isPresent()) {
            throw new ApiRequestExceptions("Username or Email already exists.");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        userRepo.save(user);

        return UserRes.convertToUserRes(user);
    }

    public UserRes getUserbyId(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ApiRequestExceptions("User with ID " + userId + " not found"));
        return UserRes.convertToUserRes(user);
    }


    public void deleteUserById(Long userId){
        if (!userRepo.existsById(userId)) {
            throw new ApiRequestExceptions("User with ID " + userId + " does not exist.");
        }

        userRepo.deleteById(userId);
    }

    public UserRes updateUser(Long userId, UserCreateRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ApiRequestExceptions("User with ID " + userId + " does not exist."));

        user.setUserName(request.getUserName() != null ? request.getUserName() : user.getUserName());
        user.setPassword(request.getPassword() != null ? request.getPassword() : user.getPassword());
        user.setEmail(request.getEmail() != null ? request.getEmail() : user.getEmail());

        User updatedUser = userRepo.save(user);
        return UserRes.convertToUserRes(updatedUser);
    }

}
