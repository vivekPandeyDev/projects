package com.vivek.pandey.api.movie.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void saveMovie() {
        Movie testMovie = Movie.builder().title("test movie")
                .director("test director")
                .studio("test studio")
                .movieCast(Set.of("test cast 1", "test cast 2"))
                .posterName("test_poster")
                .releaseYear(2000)
                .build();
        Movie savedMovie = movieRepository.save(testMovie);
        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getMovieId()).isPositive();
        assertThat(movieRepository.count()).isGreaterThan(1);
    }
}