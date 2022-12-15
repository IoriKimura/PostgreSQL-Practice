package com.example.dndwebapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters", schema = "public")
@Getter
@Setter
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long character_id;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "class_id")
    private Long class_id;
    @Column(name = "race_id")
    private Long race_id;
    @Column(name = "subrace_id")
    private Long subrace_id;
    @Column(name = "weapon_id")
    private Long weapon_id;
    @Column(name = "armour_id")
    private Long armour_id;
}
