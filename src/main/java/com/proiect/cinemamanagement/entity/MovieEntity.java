package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Movie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="duration")
    private String duration;

    @Column(name="type")
    private String type;

    @Column(name="actors")
    private String actors;

    @Column(name="director")
    private String director;

    @Column(name="cost")
    private Float cost;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RoomEntity> rooms = new ArrayList<>();

    public MovieEntity() {
        // TODO Auto-generated constructor stub
    }

    public MovieEntity(Movie movie){
        this.actors=movie.getActors();
        this.cost=movie.getCost();
        this.director=movie.getDirector();
        this.duration=movie.getDuration();
        this.name=movie.getName();
        this.type=movie.getType();
    }

    public MovieEntity(int id, String name, String duration, String type, String actors, String director, Float cost) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.actors = actors;
        this.director = director;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieEntity)) return false;
        MovieEntity that = (MovieEntity) o;
        return id == that.id &&
                name.equals(that.name) &&
                duration.equals(that.duration) &&
                type.equals(that.type) &&
                actors.equals(that.actors) &&
                director.equals(that.director) &&
                cost.equals(that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, type, actors, director, cost);
    }
}
