package com.example.demo.Repositories;

import com.example.demo.Entities.PartyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository

public interface PartiesRepository extends JpaRepository<PartyEntity, Long> {

    Page<PartyEntity> findAll(Pageable pageable);
    Optional<PartyEntity>  findById(Long id);
}