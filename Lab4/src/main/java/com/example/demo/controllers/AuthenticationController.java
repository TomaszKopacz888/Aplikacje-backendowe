package com.example.demo.controllers;

import com.example.demo.UsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    @RequestMapping("api/user/register")
    @PostMapping
    public Object register(@RequestBody UsersEntity user){

    }
}
