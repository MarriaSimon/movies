package org.fasttratckit.movies.service.movie;

import org.fasttratckit.movies.model.movie.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MovieReader {
    private final String pathName;

    public MovieReader(@Value("${initial.data}") String pathName) {
        this.pathName = pathName;
    }

    public List<Movie> readMovies() {
        List<Movie> movies = new ArrayList<>();

        try {
            File file = new File(pathName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Movie movie = parseMovie(line);
                if (movie != null) {
                    movies.add(movie);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private Movie parseMovie(String line) {
        String[] parts = line.split("\\|");

        if (parts.length >= 10) {
            String movieName = parts[0];
            int releaseYear = Integer.parseInt(parts[1]);
            int movieRating = parts[2].isEmpty() ? 0 : Integer.parseInt(parts[2]);
            String agency = parts[3].isEmpty() ? null : parts[3];
            String reviewText = parts[4].isEmpty() ? null : parts[4];
            String reviewer = parts[5].isEmpty() ? null : parts[5];
            String studioName = parts[6].isEmpty() ? null : parts[6];
            String studioAddress = parts[7].isEmpty() ? null : parts[7];
            String actorName = parts[8].isEmpty() ? null : parts[8];
            int actorBirthYear = parts.length >= 10 && !parts[9].isEmpty() ? Integer.parseInt(parts[9]) : 0;

            Movie movie = new Movie();
            movie.setName(movieName);
            movie.setYear(releaseYear);

            MovieRating movieRatingObj = new MovieRating();
            movieRatingObj.setRating(movieRating);
            movieRatingObj.setAgency(agency);
            movie.setRating(movieRatingObj);

            if (reviewText != null && reviewer != null) {
                MovieReview review = MovieReview.builder()
                        .text(reviewText)
                        .reviewer(reviewer)
                        .movie(movie)
                        .build();
                List<MovieReview> reviews = new ArrayList<>();
                reviews.add(review);
                movie.setReviews(reviews);
            }

            if (studioName != null && studioAddress != null) {
                MovieStudio studio = new MovieStudio();
                studio.setName(studioName);
                studio.setAddress(studioAddress);
                movie.setStudio(studio);
            }

            if (actorName != null && actorBirthYear != 0) {
                MovieActor actor = null;
                for (MovieActor existingActor : movie.getActors()) {
                    if (existingActor.getName().equals(actorName)) {
                        actor = existingActor;
                        break;
                    }
                }
                if (actor == null) {
                    actor = new MovieActor();
                    actor.setName(actorName);
                    actor.setBirthYear(actorBirthYear);
                    movie.getActors().add(actor);
                }
                actor.getMovies().add(movie);
            }

            return movie;
        }

        return null;
    }
}

