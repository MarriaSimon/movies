package org.fasttratckit.movies.service.movie;

import org.fasttratckit.movies.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository  extends JpaRepository<Movie, Integer > {
}
