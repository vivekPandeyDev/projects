package org.learn.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String state;
    private int zipcode;
}
