package com.vivek.managment.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Education {
    private String degreeType;
    private String institution;
    private int year;
    private  int percentage;
}
