package com.example.demo.Services;

import com.example.demo.Entities.PartyEntity;
import com.example.demo.Repositories.PartiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService {
    @Autowired
    private PartiesRepository repository;
    public List<PartyEntity> getAll(int num){
        int start= (num-1)*20;
        Pageable p= PageRequest.of(start, 10);
        Page<PartyEntity> pe= this.repository.findAll(p);
        return pe.getContent();
    }
}
