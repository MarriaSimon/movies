package org.fasttratckit.movies.service.movie;

import org.fasttratckit.movies.model.movie.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieActorsRepository extends JpaRepository<MovieActor, Integer> {
}
