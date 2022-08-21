package com.example.demo.Services;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Entities.UserLoginRequest;
import com.example.demo.Entities.UserLoginResponse;
import com.example.demo.Exceptions.NoUserException;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public long getUsersIdByEmailAndPassword(UserLoginRequest data) throws NoUserException {
        try {
            UserEntity user = ResponseEntity.of(this.repository.findByEmailAndPassword(data.getEmail(), data.getPassword())).getBody();
            assert user != null;
            return user.getId();
        } catch (Exception e) {
            throw new NoUserException();
        }
    }

}
