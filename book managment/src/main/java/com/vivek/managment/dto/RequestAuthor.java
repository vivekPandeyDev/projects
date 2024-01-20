package com.vivek.managment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vivek.managment.annotations.ValidDate;
import com.vivek.managment.formatter.CustomLocalDateDeserializer;
import jakarta.validation.constraints.NotBlank;
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
public class RequestAuthor {
    @JsonProperty("author_id")
    private UUID authorId;
    @JsonProperty("author_name")
    @NotBlank(message = "author's name cannot be blank")
    private String authorName;
    @JsonProperty("author_description")
    @NotBlank(message = "author's description cannot be blank")
    private String authorDescription;

    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonProperty("date_of_birth")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @ValidDate
    private LocalDate dob;
}
