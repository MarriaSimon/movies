package org.fasttratckit.movies.model.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class MovieActor {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "actor_name")
    private String name;

    @Column
    private int birthYear;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
