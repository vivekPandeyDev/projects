package org.learn.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;
    private String userName;
    private LocalDate dateOfBirth;
    private String email;
    private AddressDto addressDto;
}
