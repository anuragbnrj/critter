package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Customer extends User {

    private String phoneNumber;

    @Column(length = 500)
    private String notes;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Pet> pets;

}
