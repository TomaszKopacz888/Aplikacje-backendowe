package com.example.demo.Controllers;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    //echo '{"login":"exampleUser", "email":"example@email.com", "password":"password", "dateOfBirth": "1990-01-01"}' | curl -X POST -H "Content-Type:application/json" -d @- http://127.0.0.1:8080/users/create
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(this.repository.save(user));
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long userId) {
            return ResponseEntity.of(this.repository.findById(userId));
    }
}
