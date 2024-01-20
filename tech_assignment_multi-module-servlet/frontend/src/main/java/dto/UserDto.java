package dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private int userId;
    private String userName;
    private LocalDate dateOfBirth;
    private String email;
    private AddressDto primaryAddress;
    private SecondaryAddressDto secondaryAddress;
    private List<EducationDto> educationList;
}
