package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SecondaryAddress {
    private String secAddressLine1;
    private String secAddressLine2;
    private String secCity;
    private String secCountry;
    private String secState;
    private int secZipcode;
}
