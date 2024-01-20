package com.vivek.pandey.api.movie.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
