package com.vivek.pandey.api.movie.movie;

import com.vivek.pandey.api.movie.formatter.ConvertSpaceToUnderscore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovieDto implements Serializable {
    private Integer movieId;

    @NotBlank(message = "Please provide movie's title")
    private String title;

    @NotBlank(message = "Please provide movie's director name")
    private String director;

    @NotBlank(message = "Please provide movie's studio name")
    private String studio;
    private Set<String> movieCast;

    @NotNull(message = "Please provide movie's release Year, e.g 1990")
    private Integer releaseYear;

    @NotBlank(message = "Please provide movie's poster name")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please use alphabets only in poster name")
    @ConvertSpaceToUnderscore
    private String posterName;

    private String posterUrl;
}
