package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "favourite")
public class FavouriteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "userId", nullable = false)
    private long userId;
    @Column(name = "partyId", nullable = false)
    private long partyId;

    public FavouriteEntity() {
    }

    public FavouriteEntity(long userId, long partyId) {
        this.userId = userId;
        this.partyId = partyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }
}