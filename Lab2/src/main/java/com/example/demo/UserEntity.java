package com.example.demo;

public class UserEntity {
    private int id;
    private String name;

    public UserEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getAge() {
        return id;
    }

    public String getName() {
        return name;
    }
}
