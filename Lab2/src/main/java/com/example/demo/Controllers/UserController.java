package com.example.demo.Controllers;

import com.example.demo.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;


    @RequestMapping("/users")
    @ResponseBody
    public Object getUsers() {
        return this.usersService.getAllUsers();
    }

    @RequestMapping("/users/{id}/get")
    @ResponseBody
    public Object getUsers(
            @PathVariable int id
    ) {
        return this.usersService.getUserById(id);
    }

    @RequestMapping("/users/{id}/remove")
    @ResponseBody
    public Object removeUsers(
            @PathVariable int id
    ) {
        String userName=this.usersService.removeUserById(id);
        return "Removed user "+userName;
    }

    @RequestMapping("/users/add")
    @ResponseBody
    public Object addUsers(
            @RequestParam String name,
            @RequestParam Integer age
    ) {
        return this.usersService.addUser(age, name);
    }
}
