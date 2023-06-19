package org.fasttratckit.movies.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @ManyToOne
    @JoinColumn(name = "studio_id")
    private MovieStudio studio;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<MovieActor> actors;
}
