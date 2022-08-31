package com.example.demo.Controllers;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Entities.UserLoginRequest;
import com.example.demo.Exceptions.NoUserException;
import com.example.demo.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/auth")
//curl -X POST -H "Content-Type: application/json" -d '{"email":"example@email.com", "password":"password"}' http://127.0.0.1:8081/auth/login
public class AuthenticationController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<ActionResponse> loginUser(HttpServletRequest request, @RequestBody UserLoginRequest data) throws NoUserException {
        return ResponseEntity.ok(this.service.loginUser(request, data));
    }

    @GetMapping("/logout")
    public ResponseEntity<ActionResponse> logoutUser(HttpServletRequest request){
        return ResponseEntity.ok(this.service.logoutUser(request));
    }

    @PostMapping("/reg")
    public ResponseEntity<ActionResponse> registerUser(HttpServletRequest request, @RequestBody UserEntity user){
        return ResponseEntity.ok(this.service.registerUser(request, user));
    }

    @PostMapping(
            value = "/updateUserData",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ActionResponse> update(HttpServletRequest request, @RequestBody UserEntity user){
        return ResponseEntity.ok(this.service.updateUser(request, user));
    }
}