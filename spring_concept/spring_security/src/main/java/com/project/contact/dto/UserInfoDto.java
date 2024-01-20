package com.project.contact.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.contact.enums.UserRole;
import com.project.contact.validation.EnumNamePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserInfoDto {
    @Length(min = 5, message = "{user.invalid.name.length}")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{0,29}$", message = "{user.invalid.name.pattern}")
    @JsonProperty("user-name")
    private String userName;
    private String email;
    @NotBlank(message = "{user.invalid.password.blank}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&^#])[A-Za-z\\d@$!%*?&^#]{8,}$", message = "{user.invalid.password.pattern}")
    private String password;
    @NotBlank(message = "{user.invalid.name.blank}")
    @Pattern(regexp = "^[A-Z_,]*$", message = "{user.invalid.role.pattern}")
    @EnumNamePattern(enumClass = UserRole.class, message = "{user.invalid.role}")
    private String role;
}
