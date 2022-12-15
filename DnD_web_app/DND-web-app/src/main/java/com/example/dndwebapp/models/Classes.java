package com.example.dndwebapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "classes", schema = "public")
@Getter
@Setter
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long class_id;
    @Column(name = "class_name")
    private String class_name;
    @Column(name = "class_hp")
    private Integer class_hp;
    @Column(name = "class_mp")
    private Integer class_mp;
    @Column(name = "class_attribute")
    private String class_attribute;
}
