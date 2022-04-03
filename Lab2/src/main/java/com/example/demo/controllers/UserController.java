package com.example.demo.controllers;

import com.example.demo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    //private List<Integer ,UserEntity> users;

    @Autowired
    private UsersService usersService;

    public UserController() {
        //this.users=new ArrayList<>();
        this.usersService.addUser(21, "John");
        this.usersService.addUser(32, "Mike");
        this.usersService.addUser(21, "Scott");
        this.usersService.addUser(35, "Chris");
    }

    @RequestMapping("/users")
    @ResponseBody
    public Object getUsers() {
        return this.usersService.getAllUsers();
    }

    @RequestMapping("/users/{id}/get")
    @ResponseBody
    public Object getUsers(
            @PathVariable int id
    ) {
        return this.usersService.getUserById(id);
    }

    @RequestMapping("/users/add")
    @ResponseBody
    public String addUsers(
            @RequestParam String name,
            @RequestParam Integer age
    ) {
        UserEntity user = new UserEntity(age, name);
        //users.add(user);

        return "Added new user";
    }
}
