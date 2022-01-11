package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Payment {

    @NotNull private Float amount;
             private Card card;

    public Payment(@NotNull Float amount, Card card) {
        this.amount = amount;
        this.card = card;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Card getCard() {
        return card;
    }


    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return amount.equals(payment.amount) &&
                card.equals(payment.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, card);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", card=" + card +
                '}';
    }
}
