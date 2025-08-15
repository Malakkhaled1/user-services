package com.Ejada.SpringTask.Application.Models;

import lombok.Data;

@Data
public class UserRes {
    private Long id;
    private String name;
    private String email;

    public static UserRes convertToUserRes(User user) {
        UserRes userRes = new UserRes();
        userRes.id = user.getId();
        userRes.name = user.getUserName();
        userRes.email = user.getEmail();

        return userRes;
    }
}
