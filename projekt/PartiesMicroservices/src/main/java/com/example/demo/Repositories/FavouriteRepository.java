package com.example.demo.Repositories;

import com.example.demo.Entities.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Long> {

    List<FavouriteEntity> findAllByUserId(Long userId);
    FavouriteEntity findByUserIdAndPartyId(Long userId, Long partyId);
}
