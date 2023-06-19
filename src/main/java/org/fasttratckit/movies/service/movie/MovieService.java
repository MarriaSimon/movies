package org.fasttratckit.movies.service.movie;

import lombok.RequiredArgsConstructor;

import org.fasttratckit.movies.model.movie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieService {
    private MovieReader movieReader;
    private MovieRepository movieRepository;
    private MovieRatingRepository ratingRepository;
    private MovieReviewRepository reviewRepository;
    private MovieStudioRepository studioRepository;
    private MovieActorsRepository actorsRepository;

    @Autowired
    public MovieService(MovieReader movieReader, MovieRepository movieRepository, MovieRatingRepository ratingRepository,
                        MovieReviewRepository reviewRepository, MovieStudioRepository studioRepository,
                        MovieActorsRepository actorsRepository) {
        System.out.println("Post construct in Country Service");
        List<Movie> movies = movieReader.readMovies();

        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.reviewRepository = reviewRepository;
        this.studioRepository = studioRepository;
        this.actorsRepository = actorsRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, Movie updatedMovie) {
        Movie movie = getMovieById(id);
        movie.setName(updatedMovie.getName());
        movie.setYear(updatedMovie.getYear());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public MovieRating addMovieRating(Integer movieId, MovieRating movieRating) {
        Movie movie = getMovieById(movieId);
        movie.setRating(movieRating);
        movieRating.setMovie(movie);
        return ratingRepository.save(movieRating);
    }

    public MovieReview addMovieReview(Integer movieId, MovieReview movieReview) {
        Movie movie = getMovieById(movieId);
        List<MovieReview> reviews = movie.getReviews();
        reviews.add(movieReview);
        movie.setReviews(reviews);
        movieReview.setMovie(movie);
        return reviewRepository.save(movieReview);
    }

    public MovieStudio addMovieStudio(Integer movieId, MovieStudio movieStudio) {
        Movie movie = getMovieById(movieId);
        movie.setStudio(movieStudio);
        return studioRepository.save(movieStudio);
    }

    public MovieActor addMovieActor(Integer movieId, MovieActor movieActor) {
        Movie movie = getMovieById(movieId);
        List<MovieActor> actors = movie.getActors();
        actors.add(movieActor);
        movie.setActors(actors);
        return actorsRepository.save(movieActor);
    }
}
