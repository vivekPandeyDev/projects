package com.vivek.commerce.user;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "user_address")
@ToString(exclude = {"user"})
public class Address implements Serializable {

    private static final long serialVersionUID = -9097598002301777799L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String city;

    private String zipCode;

    private String state;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CommerceUser user;
}
