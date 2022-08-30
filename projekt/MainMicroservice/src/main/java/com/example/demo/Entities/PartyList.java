package com.example.demo.Entities;

import java.util.ArrayList;
import java.util.List;

public class PartyList {
    private List<PartyEntity> parties;
    public PartyList(){
        this.parties=new ArrayList<>();
    }

    public PartyList(List<PartyEntity> parties) {
        this.parties = parties;
    }

    public List<PartyEntity> getParties() {
        return parties;
    }

    public void setParties(List<PartyEntity> parties) {
        this.parties = parties;
    }
}
