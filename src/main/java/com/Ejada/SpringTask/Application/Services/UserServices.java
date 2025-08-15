package com.Ejada.SpringTask.Application.Services;

import com.Ejada.SpringTask.Application.Models.User;
import com.Ejada.SpringTask.Application.Models.UserCreateRequest;
import com.Ejada.SpringTask.Application.Models.UserRes;
import com.Ejada.SpringTask.Application.Models.UserResList;
import org.springframework.stereotype.Component;

@Component
public interface UserServices {
    UserResList getAllUsers();

    UserRes addUser(UserCreateRequest request);

    UserRes getUserbyId(Long id);

    void deleteUserById(Long id);

    UserRes updateUser(Long userId, UserCreateRequest request);

//    User findByName(String userName);
}
