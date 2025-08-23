package com.Ejada.SpringTask.application.services;

import com.Ejada.SpringTask.apis.Resources.inResponse.UserCreateRequest;
import com.Ejada.SpringTask.apis.Resources.OutResponse.UserRes;
import com.Ejada.SpringTask.apis.Resources.OutResponse.UserResList;
import com.Ejada.SpringTask.application.exceptions.UserAlreadyExistsException;
import com.Ejada.SpringTask.application.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface UserServices {
    UserResList getAllUsers();

    UserRes addUser(UserCreateRequest request) throws UserAlreadyExistsException;

    UserRes getUserbyId(Long id) throws UserNotFoundException;

    void deleteUserById(Long id) throws UserNotFoundException;

    UserRes updateUser(Long userId, UserCreateRequest request) throws UserNotFoundException;

//    User findByName(String userName);
}
