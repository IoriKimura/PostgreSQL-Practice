package com.example.dndwebapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weapons", schema = "public")
@Getter
@Setter
public class Weapons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weapon_id")
    private Long weapon_id;
    @Column(name = "weapon_name")
    private String weapon_name;
    @Column(name = "weapon_dmg")
    private Integer weapon_dmg;
    @Column(name = "weapon_mana_cost")
    private Integer weapon_mana_cost;
    @Column(name = "weapon_price")
    private Integer weapon_price;
    @Column(name = "weapon_class_id")
    private Integer weapon_class_id;
}
