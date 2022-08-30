package com.example.demo.Controllers;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/setAdmin")
    public ResponseEntity<ActionResponse> setAdmin(@RequestBody Long id){
        return ResponseEntity.ok(this.service.setAdmin(id));
    }
}
