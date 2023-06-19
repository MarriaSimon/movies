package org.fasttratckit.movies.model.movie;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
public class MovieReview {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "review")
    private String text;

    @Column
    private String reviewer;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
