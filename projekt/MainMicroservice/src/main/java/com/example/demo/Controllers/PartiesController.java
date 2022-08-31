package com.example.demo.Controllers;

import com.example.demo.Entities.ActionResponse;
import com.example.demo.Entities.PartyEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/parties")
public class PartiesController {
    @Autowired
    private PartyService service;

    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActionResponse> addParty(HttpServletRequest request, @RequestBody PartyEntity party) {
        return ResponseEntity.ok(this.service.add(request, party));
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PartyEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getById(id));
    }

    @GetMapping(value = "/page/{num}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PartyEntity>> getAll(@PathVariable Long num) {
        return ResponseEntity.ok(this.service.getAll(num));
    }

    @PostMapping(
            value = "/{id}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ActionResponse> update(HttpServletRequest request, @PathVariable long id, @RequestBody PartyEntity party) {
        party.setId(id);
        return ResponseEntity.ok(this.service.updateParty(request, party));
    }
}

