package org.fasttratckit.movies.service.movie;

import org.fasttratckit.movies.model.movie.MovieStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieStudioRepository extends JpaRepository<MovieStudio, Integer> {
}
