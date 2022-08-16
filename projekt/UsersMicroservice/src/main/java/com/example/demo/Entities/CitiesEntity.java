package com.example.demo.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class CitiesEntity {

    @Id
    private int id;
    private String city;
}
