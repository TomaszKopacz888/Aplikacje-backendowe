package com.example.demo.controllers;

import com.example.demo.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.UsersService;


@Controller
public class UsersController {
    @Autowired
    private UsersService userService;

    @RequestMapping("/api/users")
    @ResponseBody
    public Object getUsers(
            @RequestParam(defaultValue = "1") int page_number,
            @RequestParam(defaultValue = "1") int page_size
    ) {
        this.userService.setPageNumber(page_number);
        this.userService.setPageSize(page_size);
        return this.userService.getPageData();
    }


//    @RequestMapping(
//            value = "/api/create",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    @PostMapping("")
//    public UsersEntity createUser(){
//        return new UsersEntity(100, "Matt", 19);
//    }
//
//

}
