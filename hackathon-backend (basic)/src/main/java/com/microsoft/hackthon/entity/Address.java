package com.microsoft.hackthon.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    private String addressLine;
    private String city;
    private String state;
    private String country;
}
