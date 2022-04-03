package com.example.demo;

public class UserEntity {
    private int age;
    private String name;

    public UserEntity(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
