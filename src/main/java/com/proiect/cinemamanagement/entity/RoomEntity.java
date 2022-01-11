package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Room;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name="capacity")
    private Integer capacity;
    @Column(name="row_no")
    private Integer rowNumber;
    @Column(name="seats_number")
    private Integer seatsNumber;

    public RoomEntity(){}

    public RoomEntity(Room room){
        this.capacity=room.getCapacity();
        this.seatsNumber=room.getSeatsNumber();
        this.rowNumber=room.getRowNumber();
        this.name=room.getName();
    }

    public RoomEntity(int id, String name, Integer capacity, Integer rowNumber, Integer seatsNumber) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.rowNumber = rowNumber;
        this.seatsNumber = seatsNumber;
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
        if (!(o instanceof RoomEntity)) return false;
        RoomEntity that = (RoomEntity) o;
        return id == that.id &&
                name.equals(that.name) &&
                capacity.equals(that.capacity) &&
                rowNumber.equals(that.rowNumber) &&
                seatsNumber.equals(that.seatsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, rowNumber, seatsNumber);
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", rowNumber=" + rowNumber +
                ", seatsNumber=" + seatsNumber +
                '}';
    }
}
