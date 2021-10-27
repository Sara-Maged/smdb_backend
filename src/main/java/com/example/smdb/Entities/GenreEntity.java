package com.example.smdb.Entities;

import javax.persistence.*;

@Entity
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int genre_id;
    private int id;



    public GenreEntity() {
    }

    public GenreEntity(int id) {
        this.id = id;
    }

}
