package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Schedule;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name="movie_name")
    private String movieName;

    @Column(name="start_date")
    private String startDate;

    @Column(name="room_name")
    private String roomName;

    @Column(name="price")
    private Float price;

    public ScheduleEntity(){}

    public ScheduleEntity(Schedule schedule){
        this.movieName=schedule.getMovieName();
        this.startDate=schedule.getStartDate();
        this.roomName=schedule.getRoomName();
        this.price=schedule.getPrice();
    }

    public ScheduleEntity(int id, String movieName, String startDate, String roomName, Float price) {
        this.id = id;
        this.movieName = movieName;
        this.startDate = startDate;
        this.roomName = roomName;
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
        if (!(o instanceof ScheduleEntity)) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return id == that.id &&
                movieName.equals(that.movieName) &&
                startDate.equals(that.startDate) &&
                roomName.equals(that.roomName) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieName, startDate, roomName, price);
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", roomName='" + roomName + '\'' +
                ", price=" + price +
                '}';
    }
}
