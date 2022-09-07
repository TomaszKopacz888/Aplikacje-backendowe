package com.example.demo.Entities;

import javax.persistence.*;

public class FavouriteEntity {

    private long userId;
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