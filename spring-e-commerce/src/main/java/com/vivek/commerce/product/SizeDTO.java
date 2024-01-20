package com.vivek.commerce.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SizeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3532532432423L;

    @NotBlank(message = "Size name cannot be blank")
    private String name;

    @NotBlank(message = "Size quantity cannot be blank")
    private String quantity;
}