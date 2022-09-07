package com.example.demo.Controllers;

import com.example.demo.Entities.FavouriteEntity;
import com.example.demo.Entities.FavouriteList;
import com.example.demo.Services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/favourite")
public class FavouriteController {
    @Autowired
    private FavouriteService service;

    @GetMapping(value = "/get")
    public ResponseEntity<List<FavouriteEntity>> getFav(HttpServletRequest request) {
        return ResponseEntity.ok(this.service.get(request));
    }

}
