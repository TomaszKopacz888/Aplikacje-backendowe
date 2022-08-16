package com.example.demo.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="typesOfParty")
public class TypesOfPartyEntity {
    //SET ENUM!!!!!!!!!!!!!!
    @Id
    private String type;
}
