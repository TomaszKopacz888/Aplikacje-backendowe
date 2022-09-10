package com.example.demo.Entities;

import java.util.List;

public class FavouritesList {
    private List<FavouriteEntity> list;

    public FavouritesList() {
    }

    public FavouritesList(List<FavouriteEntity> list) {
        this.list = list;
    }

    public List<FavouriteEntity> getList() {
        return list;
    }

    public void setList(List<FavouriteEntity> list) {
        this.list = list;
    }
}
