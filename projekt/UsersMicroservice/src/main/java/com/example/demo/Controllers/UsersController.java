package com.example.demo.Controllers;

import com.example.demo.Entities.AdminId;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Entities.UserLoginRequest;
import com.example.demo.Exceptions.NoUserException;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(this.repository.save(user));
    }
    //curl -X POST -H "Content-Type: application/json" -d '{"email":"example@email.com", "password":"password"}' http://127.0.0.1:8082/users/getIdByEmailAndPassword
    @PostMapping(value = "/getIdByEmailAndPassword")

        public long createUser(@RequestBody UserLoginRequest data) throws NoUserException {
            try {

                return service.getUsersIdByEmailAndPassword(data);
            }
            catch (Exception e){
                throw new NoUserException();
            }
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
            if (user.getPassword()!=null) foundUserEntity.setPassword(user.getPassword());
            if (user.getName()!=null) foundUserEntity.setName(user.getName());
            if (user.getSurname()!=null) foundUserEntity.setSurname(user.getSurname());
            this.repository.save(foundUserEntity);
        }
        return ResponseEntity.of(foundUserOptional);
    }
    @PostMapping(
            value = "/makeAdmin",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public boolean setUserAdmin(@RequestBody AdminId id){
        try {
            Optional<UserEntity> foundUserOptional=this.repository.findById(id.getId());
            if (foundUserOptional.isPresent()) {
                UserEntity user = foundUserOptional.get();
                user.setType("ADMIN");
                this.repository.save(user);
                return true;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
}
