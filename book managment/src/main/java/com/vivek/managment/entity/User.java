package com.vivek.managment.entity;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private int userId;
    private String userName;
    private LocalDate dateOfBirth;
    private String email;
    private Address primaryAddress;
    private SecondaryAddress secondaryAddress;
    private List<Education> educationList;
}
