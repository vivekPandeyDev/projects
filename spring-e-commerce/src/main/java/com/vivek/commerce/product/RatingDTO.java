package com.vivek.commerce.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RatingDTO {
    
    @Min(1)
    @Max(5)
    private Integer value;
}
