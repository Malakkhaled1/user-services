package com.Ejada.SpringTask.application.services.impl;

import com.Ejada.SpringTask.application.exceptions.UserAlreadyExistsException;
import com.Ejada.SpringTask.application.exceptions.UserNotFoundException;
import com.Ejada.SpringTask.application.models.User;
import com.Ejada.SpringTask.apis.Resources.inResponse.UserCreateRequest;
import com.Ejada.SpringTask.apis.Resources.OutResponse.UserRes;
import com.Ejada.SpringTask.apis.Resources.OutResponse.UserResList;
import com.Ejada.SpringTask.application.repositories.UserRepo;
import com.Ejada.SpringTask.application.services.UserServices;
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
            throw new UserAlreadyExistsException();
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
                .orElseThrow(() -> new UserNotFoundException());
        return UserRes.convertToUserRes(user);
    }


    public void deleteUserById(Long userId){
        if (!userRepo.existsById(userId)) {
            throw new UserNotFoundException();
        }

        userRepo.deleteById(userId);
    }

    public UserRes updateUser(Long userId, UserCreateRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() ->  new UserNotFoundException());

        user.setUserName(request.getUserName() != null ? request.getUserName() : user.getUserName());
        user.setPassword(request.getPassword() != null ? request.getPassword() : user.getPassword());
        user.setEmail(request.getEmail() != null ? request.getEmail() : user.getEmail());

        User updatedUser = userRepo.save(user);
        return UserRes.convertToUserRes(updatedUser);
    }

}
