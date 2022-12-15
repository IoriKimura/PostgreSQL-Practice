package com.example.dndwebapp.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "armours")
@Getter
@Setter
public class Armours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "armour_id")
    private Long armour_id;
    @Column(name = "armour_name")
    private String armour_name;
    @Column(name = "armour_points")
    private Integer armour_points;
    @Column(name = "armour_price")
    private Double armour_price;
    @Column(name = "armour_class_id")
    private Integer armour_class_id;
}
