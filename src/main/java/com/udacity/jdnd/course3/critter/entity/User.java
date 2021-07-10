package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

// Using MappedSuperClass as polymorphic queries are not required
@MappedSuperclass
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=500)
    @Nationalized
    private String name;

}
