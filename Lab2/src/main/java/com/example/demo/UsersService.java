package com.example.demo;

import com.example.demo.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService {
    private final Map<Integer, UserEntity> users;
    private int lastId;

    public UsersService() {
        this.users = new HashMap<>();
        this.users.put(0, new UserEntity(21, "John"));
        this.users.put(1, new UserEntity(32, "Mike"));
        this.users.put(2, new UserEntity(21, "Scott"));
        this.users.put(3, new UserEntity(35, "Chris"));
        this.lastId = 4;
    }

    public Object getAllUsers() {
        return this.users.values();
    }

    public Object getUserById(int id) {
        if (this.users.containsKey(id)) return this.users.get(id);
        else return "There is no user id " + id;
    }

    public String removeUserById(int id) {
        if (this.users.containsKey(id)) {
            UserEntity user = users.get(id);
            this.users.remove(id);
            return user.getName();
        }
        else return "There is no user id " + id;

    }

    public UserEntity addUser(int age, String name) {
        UserEntity user = new UserEntity(age, name);
        this.users.put(this.lastId, user);
        this.lastId++;
        return user;
    }
}
