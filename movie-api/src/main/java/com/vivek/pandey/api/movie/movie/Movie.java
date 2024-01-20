package com.vivek.pandey.api.movie.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieId;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Please provide movie's title")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's director name")
    private String director;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's studio name")
    private String studio;

    @ElementCollection()
    @CollectionTable(name = "movie_cast", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "cast_member")
    private Set<String> movieCast;

    @Column(nullable = false)
    @NotNull(message = "Please provide movie's release Year, e.g 1990")
    private Integer releaseYear;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's poster name")
    private String posterName;

}
