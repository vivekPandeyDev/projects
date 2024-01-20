package com.vivek.integrated.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentDTO {

    private Long id;

    @Size(max = 255)
    private String createdBy;

    @Size(max = 255)
    private String updatedBy;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

}
