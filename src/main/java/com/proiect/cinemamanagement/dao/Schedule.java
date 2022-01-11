package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Schedule {

    @NotNull private String movieName;
    @NotNull private String startDate;
    @NotNull private String roomName;
    @NotNull private Float price;

    public Schedule(@NotNull String movieName, @NotNull String startDate, @NotNull String roomName, @NotNull Float price) {
        this.movieName = movieName;
        this.startDate = startDate;
        this.roomName = roomName;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return movieName.equals(schedule.movieName) &&
                startDate.equals(schedule.startDate) &&
                roomName.equals(schedule.roomName) &&
                price.equals(schedule.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, startDate, roomName, price);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "movieName='" + movieName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", roomName='" + roomName + '\'' +
                ", price=" + price +
                '}';
    }
}
