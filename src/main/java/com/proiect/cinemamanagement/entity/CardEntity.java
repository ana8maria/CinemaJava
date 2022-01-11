package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Card;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="expiration_date")
    private String expirationDate;

    @Column(name="cvv")
    private Integer CVV;


    public CardEntity(int id, String firstName, String lastName, String expirationDate, Integer CVV) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
        this.CVV = CVV;
    }

    public CardEntity(Card card){
        this.firstName= card.getFirstName();
        this.lastName= card.getLastName();
        this.expirationDate= card.getExpirationDate();
        this.CVV= card.getCVV();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCVV() {
        return CVV;
    }

    public void setCVV(Integer CVV) {
        this.CVV = CVV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardEntity)) return false;
        CardEntity cardEntity = (CardEntity) o;
        return id == cardEntity.id &&
                firstName.equals(cardEntity.firstName) &&
                lastName.equals(cardEntity.lastName) &&
                expirationDate.equals(cardEntity.expirationDate) &&
                CVV.equals(cardEntity.CVV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, expirationDate, CVV);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expirationDate=" + expirationDate +
                ", CVV=" + CVV +
                '}';
    }
}
