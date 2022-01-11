package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Card {

    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private String expirationDate;
    @NotNull private Integer CVV;

    public Card(@NotNull String firstName, @NotNull String lastName, @NotNull String expirationDate, @NotNull Integer CVV) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
        this.CVV = CVV;
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
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return firstName.equals(card.firstName) &&
                lastName.equals(card.lastName) &&
                expirationDate.equals(card.expirationDate) &&
                CVV.equals(card.CVV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, expirationDate, CVV);
    }

    @Override
    public String toString() {
        return "Card{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expirationDate=" + expirationDate +
                ", CVV=" + CVV +
                '}';
    }
}
