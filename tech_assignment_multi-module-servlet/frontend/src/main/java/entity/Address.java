package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String state;
    private int zipcode;
}
