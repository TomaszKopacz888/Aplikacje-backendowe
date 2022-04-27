package com.example.demo.controllers;

import com.example.demo.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.UsersService;

import java.util.function.ObjIntConsumer;


@RestController
public class UsersController {
    @Autowired
    private UsersService userService;

    //curl -X GET -i http://127.0.0.1:8080/api/users
    @RequestMapping("/api/users")
    @GetMapping
    public Object getUsers(
            @RequestParam(defaultValue = "1") int page_number,
            @RequestParam(defaultValue = "1") int page_size
    ) {
        this.userService.setPageNumber(page_number);
        this.userService.setPageSize(page_size);
        return this.userService.getPageData();
    }

    //curl -X POST -H "Content-Type: application/json"  -d '{"name":"Scott", "email":"scott1@mail.pl"}' http://127.0.0.1:8080/api/user/create
    @RequestMapping(
            value = "/api/user/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping
    public Object createUser(@RequestBody UsersEntity user) {

        return this.userService.addUser(user);
    }

    @RequestMapping("/api/users/{id}")
    @GetMapping
    public Object getUserById(@PathVariable int id) {
        return this.userService.getUserById(id);
    }

    //curl -X POST -H "Content-Type: application/json"  -d '{"name":"Scott","email":"scott442@gmail.com"}' http://127.0.0.1:8080/api/users/4/update
    @RequestMapping(
            value="/api/users/{id}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping
    public Object updateUser(@PathVariable int id, @RequestBody UsersEntity user){
        return this.userService.updateUser(id, user);
    }

    @RequestMapping(
            value= "/api/users/{id}/remove",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @GetMapping
    public Object removeUserById(@PathVariable int id)
    {
        return this.userService.removeUser(id);
    }
}
