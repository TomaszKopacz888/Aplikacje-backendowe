package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name="parties")
public class PartyEntity {
    @Id
    private int id;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private TypesOfPartyEntity type;
    private String title;
    private String dateTime;
    private String description;
    private String pictures;
    @ManyToOne
    private CitiesEntity city;
    private String address;
}
