package org.fasttratckit.movies.model.movie;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class Movie {
    //Create Movie(id:Integer, name:String, year:int) entity
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "movie_name")
    private String name;

    @Column
    private int year;

    @OneToOne(mappedBy = "movie", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private MovieRating rating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieReview> reviews;

//    public void addReview(MovieReview review) {
//        if (reviews == null) {
//            reviews = new ArrayList<>();
//        }
//        reviews.add(review);
//        review.setMovie(this);
//    }

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private MovieStudio studio;


    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<MovieActor> actors;
}
