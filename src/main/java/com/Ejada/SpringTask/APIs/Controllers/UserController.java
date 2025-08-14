package com.Ejada.SpringTask.APIs.Controllers;


import com.Ejada.SpringTask.Application.Models.User;
import com.Ejada.SpringTask.Application.Services.Impl.UserServicesImpl;
import com.Ejada.SpringTask.Application.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/user")
    public void getAllUsers() {
        userServices.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public void getUser(@PathVariable int userId){
        userServices.getUserbyId(userId);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userServices.addUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userServices.deleteUserById(userId);
    }

    @PutMapping("/user/{userId}")
    public void updateUserById(@PathVariable int userId, @RequestBody User user) {
        userServices.updateUserById(userId, user);
    }

}
