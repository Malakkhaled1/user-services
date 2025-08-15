package com.Ejada.SpringTask.APIs.Controllers;


import com.Ejada.SpringTask.Application.Models.User;
import com.Ejada.SpringTask.Application.Models.UserCreateRequest;
import com.Ejada.SpringTask.Application.Models.UserRes;
import com.Ejada.SpringTask.Application.Models.UserResList;
import com.Ejada.SpringTask.Application.Services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<UserResList> getAllUsers() {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRes> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(userServices.getUserbyId(userId));
    }

    @PostMapping
    public ResponseEntity<UserRes> addUser(@RequestBody @Valid UserCreateRequest request) {
        UserRes userRes = userServices.addUser(request);
        return ResponseEntity.ok(userRes);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userServices.deleteUserById(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserRes> updateUserById(@PathVariable Long userId,@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.ok(userServices.updateUser(userId, request));
    }

}
