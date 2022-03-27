package com.example.demo.controllers;

import com.example.demo.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<UserEntity> users;

    public UserController(){
        this.users=new ArrayList<>();
        users.add(new UserEntity(0,"John"));
        users.add(new UserEntity(1, "Mike"));
        users.add(new UserEntity(2, "Scott"));
        users.add(new UserEntity(3, "Chris"));
    }
    @RequestMapping("/users")
    @ResponseBody
    public Object getUsers(){

        return this.users;
    }

    @RequestMapping("/users/{id}")
    public Object getUsers(
        @PathVariable Long id
    ){
        return this.users;
    }

    @RequestMapping("/user/add")
    public void addUsers(
            @RequestParam String name,
            @RequestParam Integer id
    ){
        UserEntity user=new UserEntity(id, name);
        users.add(user);
    }
}
