package org.fasttratckit.movies.service.movie;

import org.fasttratckit.movies.model.movie.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, Integer> {
}
