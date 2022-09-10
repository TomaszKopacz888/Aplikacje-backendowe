package com.example.demo.Controllers;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.FavouriteEntity;
import com.example.demo.Entities.PartyEntity;
import com.example.demo.Services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/favourite")
public class FavouriteController {
    @Autowired
    private FavouriteService service;

    @GetMapping(value = "/get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PartyEntity>> getFavourite(HttpServletRequest request) {
        return ResponseEntity.ok(this.service.get(request));
    }

    @PostMapping(value = "/set",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ActionResponse> setFavourite(HttpServletRequest request, @RequestBody FavouriteEntity favourite){
        return ResponseEntity.ok(this.service.add(request, favourite));
    }
}
