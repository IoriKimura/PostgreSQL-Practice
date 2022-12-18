package com.example.dndwebapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
    @Column(name = "race_name")
    private String race_name;
    @Column(name = "subrace_name")
    private String subrace_name;
}
