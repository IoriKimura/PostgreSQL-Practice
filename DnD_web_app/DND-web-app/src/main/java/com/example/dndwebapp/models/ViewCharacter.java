package com.example.dndwebapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "characterCard", schema = "public")
@Getter
@Setter
public class ViewCharacter {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "character_name")
    private String character_name;

    @Column(name = "class_name")
    private String class_name;
    @Column(name = "class_attribute")
    private String class_attribute;

    @Column(name = "hp")
    private Integer hp;
    @Column(name = "mp")
    private Integer mp;

    @Column(name = "race_name")
    private String race_name;
    @Column(name = "race_attribute")
    private String race_attribute;
    @Column(name = "subrace_name")
    private String subrace_name;
    @Column(name = "subrace_attribute")
    private String subrace_attribute;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "weapon_name")
    private String weapon_name;
    @Column(name = "weapon_dmg")
    private  Integer weapon_dmg;
    @Column(name = "weapon_mana_cost")
    private Integer weapon_mana_cost;
    @Column(name = "weapon_price")
    private Float weapon_price;

    @Column(name = "armour_name")
    private String armour_name;
    @Column(name = "armour_points")
    private Integer armour_points;
    @Column(name = "armour_price")
    private Float armour_price;
}
