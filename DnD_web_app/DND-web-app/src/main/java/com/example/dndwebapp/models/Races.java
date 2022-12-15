package com.example.dndwebapp.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "races", schema = "public")
@Getter
@Setter
public class Races {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id")
    private Long race_id;
    @Column(name = "race_name")
    private String race_name;
    @Column(name = "race_description")
    private String race_description;
    @Column(name = "race_attribute")
    private String race_attribute;
    @Column(name = "race_hp")
    private Integer race_hp;
    @Column(name = "race_mp")
    private Integer race_mp;
}
