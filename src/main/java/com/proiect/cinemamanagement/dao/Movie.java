package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Movie {

    @NotNull private String name;
    @NotNull private String duration;
    @NotNull private String type;
    @NotNull private String actors;
    @NotNull private String director;
    @NotNull private Float cost;

    public Movie(@NotNull String name, @NotNull String duration, @NotNull String type, @NotNull String actors, @NotNull String director, @NotNull Float cost) {
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.actors = actors;
        this.director = director;
        this.cost = cost;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.name) &&
                duration.equals(movie.duration) &&
                type.equals(movie.type) &&
                actors.equals(movie.actors) &&
                director.equals(movie.director) &&
                cost.equals(movie.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, type, actors, director, cost);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", cost=" + cost +
                '}';
    }
}
