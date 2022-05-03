package com.example.demo;

import org.apache.catalina.User;
//
//{"id":0,"name":"Scott","email":"scott442@gmail.com"}
//{"id":1,"name":"Matt","email":"matt1@mail.pl"}
//{"id":2,"name":"Thomas","email":"thomas1@mail.pl"}
//{"id":3,"name":"Mark","email":"mark1@mail.pl"}
public class UsersEntity {
    private int id=0;
    private String name;
    private String email;

    public UsersEntity(){

    }
    public UsersEntity(int id, String name, String email) {
        this.id=id;
        this.name = name;
        this.email = email;
    }
    public UsersEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAge(String email) {
        this.email = email;
    }
}
