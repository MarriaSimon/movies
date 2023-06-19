package org.fasttratckit.movies.controller;

import org.fasttratckit.movies.model.movie.*;
import org.fasttratckit.movies.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {


    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        Movie movie = movieService.updateMovie(id, updatedMovie);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{movieId}/rating")
    public ResponseEntity<Movie> addMovieRating(
            @PathVariable Integer movieId, @RequestBody MovieRating movieRating) {
        MovieRating addedRating = movieService.addMovieRating(movieId, movieRating);
        return ResponseEntity.ok(addedRating.getMovie());
    }

    @PostMapping("/{movieId}/review")
    public ResponseEntity<Movie> addMovieReview(
            @PathVariable Integer movieId, @RequestBody MovieReview movieReview) {
        MovieReview addedReview = movieService.addMovieReview(movieId, movieReview);
        return ResponseEntity.ok(addedReview.getMovie());
    }

    @PostMapping("/{movieId}/studio")
    public ResponseEntity<List<Movie>> addMovieStudio(
            @PathVariable Integer movieId, @RequestBody MovieStudio movieStudio) {
        MovieStudio addedStudio = movieService.addMovieStudio(movieId, movieStudio);
        return ResponseEntity.ok(addedStudio.getMovies());
    }

    @PostMapping("/{movieId}/actor")
    public ResponseEntity<MovieActor> addMovieActor(@PathVariable Integer movieId, @RequestBody MovieActor movieActor) {
        MovieActor addedActor = movieService.addMovieActor(movieId, movieActor);
        return ResponseEntity.ok(addedActor);
    }
}

