package com.vivek.commerce.product.category;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 23532532532253L;

    @NotBlank(message = "Category name cannot be blank")
    private String name;

    private CategoryDTO parentCategory;

}
