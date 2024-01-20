package com.vivek.integrated.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Student extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

}
