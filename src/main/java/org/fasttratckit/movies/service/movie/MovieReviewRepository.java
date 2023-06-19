package org.fasttratckit.movies.service.movie;

import org.fasttratckit.movies.model.movie.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Integer> {
}
