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
import java.util.Optional;

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

    @PostMapping(
            value = "{id}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long id, @RequestBody UserEntity user){
        Optional<UserEntity> foundUserOptional=this.repository.findById(id);
        if (foundUserOptional.isPresent()){
            UserEntity foundUserEntity=foundUserOptional.get();
            if (user.getEmail()!=null) foundUserEntity.setEmail(user.getEmail());
            if (user.getLogin()!=null) foundUserEntity.setLogin((user.getLogin()));
            if (user.getPassword()!=null) foundUserEntity.setPassword(user.getPassword());
            if (user.getName()!=null) foundUserEntity.setName(user.getName());
            if (user.getSurname()!=null) foundUserEntity.setSurname(user.getSurname());
            this.repository.save(foundUserEntity);
        }
        return ResponseEntity.of(foundUserOptional);
    }


}
