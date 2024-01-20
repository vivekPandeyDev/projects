package org.learn.entity;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private int userId;
    private String userName;
    private LocalDate date;
    private String email;
    private Address address;
}
