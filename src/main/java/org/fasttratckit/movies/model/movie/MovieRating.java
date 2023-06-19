package org.fasttratckit.movies.model.movie;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class MovieRating {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private int rating;
    @Column(name = "agency_name")
    private String agency;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
