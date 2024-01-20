package com.vivek.managment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vivek.managment.annotations.ValidDate;
import com.vivek.managment.entity.Author;
import com.vivek.managment.formatter.CustomLocalDateDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBook {
    @JsonProperty("book_id")
    private UUID bookId;
    @JsonProperty("book_name")
    @NotBlank(message = "book's name cannot be blank")
    private String bookName;
    @JsonProperty("book_price")
    @NotNull(message = "book's price cannot be null")
    private Double bookPrice;
    @ValidDate
    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonProperty("publish_date")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @NotNull(message = "book's publish date cannot be null")
    private LocalDate publishDate;
    @NotNull(message = "author id cannot be null")
    private UUID authorId;
}
