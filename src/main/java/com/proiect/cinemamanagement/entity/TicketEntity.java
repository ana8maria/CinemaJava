package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Ticket;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name="movie_name")
    private String movieName;
    @Column(name="date_and_time")
    private String dateAndTime;
    @Column(name="room_name")
    private String roomName;
    @Column(name="row_no")
    private Integer row;
    @Column(name="seat_number")
    private Integer seatsNumber;
    @Column(name="price")
    private Float price;

    public TicketEntity(){}

    public TicketEntity(Ticket ticket){
        this.movieName=ticket.getMovieName();
        this.dateAndTime=ticket.getDateAndTime();
        this.roomName=ticket.getRoomName();
        this.row=ticket.getRow();
        this.seatsNumber=ticket.getSeatsNumber();
        this.price=ticket.getPrice();
    }

    public TicketEntity(int id, String movieName, String dateAndTime, String roomName, Integer row, Integer seatsNumber, Float price) {
        this.id = id;
        this.movieName = movieName;
        this.dateAndTime = dateAndTime;
        this.roomName = roomName;
        this.row = row;
        this.seatsNumber = seatsNumber;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(o instanceof TicketEntity)) return false;
        TicketEntity that = (TicketEntity) o;
        return id == that.id &&
                movieName.equals(that.movieName) &&
                dateAndTime.equals(that.dateAndTime) &&
                roomName.equals(that.roomName) &&
                row.equals(that.row) &&
                seatsNumber.equals(that.seatsNumber) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieName, dateAndTime, roomName, row, seatsNumber, price);
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", roomName='" + roomName + '\'' +
                ", row=" + row +
                ", seatsNumber=" + seatsNumber +
                ", price=" + price +
                '}';
    }
}
