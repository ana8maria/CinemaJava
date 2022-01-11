package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Ticket {

    @NotNull private String movieName;
    @NotNull private String dateAndTime;
    @NotNull private String roomName;
     private Integer row;
     private Integer seatsNumber;
     private Float price;

    public Ticket(@NotNull String movieName, @NotNull String dateAndTime, @NotNull String roomName,  Integer row,  Integer seatsNumber, @NotNull Float price) {
        this.movieName = movieName;
        this.dateAndTime = dateAndTime;
        this.roomName = roomName;
        this.row = row;
        this.seatsNumber = seatsNumber;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
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
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(movieName, ticket.movieName) &&
                Objects.equals(dateAndTime, ticket.dateAndTime) &&
                Objects.equals(roomName, ticket.roomName) &&
                Objects.equals(row, ticket.row) &&
                Objects.equals(seatsNumber, ticket.seatsNumber) &&
                Objects.equals(price, ticket.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, dateAndTime, roomName, row, seatsNumber, price);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "movieName='" + movieName + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", roomName='" + roomName + '\'' +
                ", row=" + row +
                ", seatsNumber=" + seatsNumber +
                ", price=" + price +
                '}';
    }
}
