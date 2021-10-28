package com.example.smdb.Entities;

import javax.persistence.*;

@Entity
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int genre_id;
    private int id;

//    @ManyToOne
//    //@JoinColumn//("movie_ids_id")
//    private int movieId;

    public GenreEntity() {
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
