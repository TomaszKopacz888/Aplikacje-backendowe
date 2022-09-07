package com.example.demo.Entities;

import java.util.ArrayList;
import java.util.List;

public class FavouriteList {
    private List<FavouriteEntity> fav;

    public FavouriteList() {
        this.fav = new ArrayList<>();
    }

    public FavouriteList(List<FavouriteEntity> parties) {
        this.fav = parties;
    }

    public List<FavouriteEntity> getFav() {
        return fav;
    }

    public void setFav(List<FavouriteEntity> fav) {
        this.fav = fav;
    }
}
