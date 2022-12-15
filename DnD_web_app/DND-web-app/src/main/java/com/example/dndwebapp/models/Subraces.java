package com.example.dndwebapp.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subraces", schema = "public")
@Getter
@Setter
public class Subraces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subrace_id")
    private Long subrace_id;
    @Column(name = "race_id")
    private Long race_id;
    @Column(name = "subrace_name")
    private String subrace_name;
    @Column(name = "subrace_attribute")
    private String subrace_attribute;
}
