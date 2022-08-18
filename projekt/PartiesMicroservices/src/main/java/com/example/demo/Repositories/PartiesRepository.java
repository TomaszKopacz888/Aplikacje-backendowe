package com.example.demo.Repositories;

import com.example.demo.Entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PartiesRepository extends JpaRepository<PartyEntity, Long> {
    List<PartyEntity> findAll();
    Optional<PartyEntity>  findById(Long id);
}