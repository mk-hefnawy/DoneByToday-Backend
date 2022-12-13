package com.hefnawy.DoneByToday.user_feature.controller;

import com.hefnawy.DoneByToday.security.JwtUtils;
import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import com.hefnawy.DoneByToday.user_feature.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping(value = "{userName}")
    public User getUserByUserName(@PathVariable String userName){
        return userService.getUserByUserName(userName);
    }

    @PostMapping(value = "")
    public User addNewUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/logout")
    public String logout(@RequestHeader(value = "Authorization") String authorization){
        String token = authorization.substring("Bearer ".length());
        new JwtUtils().expireToken(token);
        return "Logged out";
    }
}
