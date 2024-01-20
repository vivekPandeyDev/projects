package dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SecondaryAddressDto {
    private String secAddressLine1;
    private String secAddressLine2;
    private String secCity;
    private String secCountry;
    private String secState;
    private int secZipcode;
}
