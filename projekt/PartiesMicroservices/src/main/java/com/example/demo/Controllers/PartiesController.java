package com.example.demo.Controllers;

import com.example.demo.Entities.PartyEntity;
import com.example.demo.Repositories.PartiesRepository;
import com.example.demo.Services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/parties")
public class PartiesController {

    @Autowired
    private PartiesRepository partiesRepository;

    @Autowired
    private PartyService service;


    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<PartyEntity> getParty(@PathVariable("id") Long userId) {
        return ResponseEntity.of(this.partiesRepository.findById(userId));
    }

    @GetMapping(
            value = "/all/{num}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<List<PartyEntity>> getAll(@PathVariable int num) {
        return ResponseEntity.ok(this.service.getAll(num));
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PartyEntity> createParty(@RequestBody PartyEntity party) {
        return ResponseEntity.ok(this.partiesRepository.save(party));
    }

    @PostMapping(
            value = "{id}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PartyEntity> updateParty(@PathVariable("id") Long id, @RequestBody PartyEntity party) {
        Optional<PartyEntity> foundPartyOptional = this.partiesRepository.findById(id);
        if (foundPartyOptional.isPresent()) {
            PartyEntity foundPartyEntity = foundPartyOptional.get();
            if (party.getTitle() != null) foundPartyEntity.setTitle(party.getTitle());
            if (party.getDescription() != null) foundPartyEntity.setDescription((party.getDescription()));
            if (party.getPictures() != null) foundPartyEntity.setPictures(party.getPictures());
            if (party.getAddress() != null) foundPartyEntity.setAddress(party.getAddress());
        }
        return ResponseEntity.of(foundPartyOptional);
    }
}
