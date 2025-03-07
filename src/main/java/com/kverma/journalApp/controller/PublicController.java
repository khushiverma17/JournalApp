package com.kverma.journalApp.controller;

import com.kverma.journalApp.entity.User;
import com.kverma.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("create-user")
    public void createUser(@RequestBody User user){
        System.out.println("helldofidj");
        userService.saveNewUser(user);
    }




}