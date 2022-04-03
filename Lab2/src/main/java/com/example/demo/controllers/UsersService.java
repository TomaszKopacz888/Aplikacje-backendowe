package com.example.demo.controllers;

import com.example.demo.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService {
    private Map<Integer, UserEntity> users;
    private int lastId=0;
    public UsersService(){
        this.users=new HashMap<>();
    }

    public Object getAllUsers(){
        return this.users;
    }
    public Object getUserById(int id) {
        if (this.users.containsKey(id)) return this.users.get(id);
        else return "There is no user id " + id;

    }
    public void addUser(int age, String name){
        this.users.put(this.lastId, new UserEntity(age, name));
        this.lastId++;
    }
}
