package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Room {

    @NotNull private String name;
    @NotNull private Integer capacity;
    @NotNull private Integer rowNumber;
    @NotNull private Integer seatsNumber;


    public Room(@NotNull String name, @NotNull Integer capacity, @NotNull Integer rowNumber, @NotNull Integer seatsNumber) {

        this.name = name;
        this.capacity = capacity;
        this.rowNumber = rowNumber;
        this.seatsNumber = seatsNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return name.equals(room.name) &&
                capacity.equals(room.capacity) &&
                rowNumber.equals(room.rowNumber) &&
                seatsNumber.equals(room.seatsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, rowNumber, seatsNumber);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", rowNumber=" + rowNumber +
                ", seatsNumber=" + seatsNumber +
                '}';
    }
}
