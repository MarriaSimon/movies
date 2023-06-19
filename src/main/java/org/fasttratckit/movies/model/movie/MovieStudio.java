package org.fasttratckit.movies.model.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class MovieStudio {

    @Id
    @GeneratedValue
    private Integer id;

        @Column(name = "studio_name")
    private String name;

        @Column
    private String address;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
    private List<Movie> movies;

}
