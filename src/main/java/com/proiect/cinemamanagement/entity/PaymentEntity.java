package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Payment;

import javax.persistence.*;


@Entity
@Table(name="payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name="amount")
    private Float amount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card")
    private CardEntity card;

    public PaymentEntity(){}

    public PaymentEntity(Float amount, CardEntity card){
        this.amount = amount;
        this.card = card;
    }

    public PaymentEntity(Payment payment){
        this.amount=payment.getAmount();
        this.card=  new CardEntity(payment.getCard());
    }

    public PaymentEntity(int id, Float amount, CardEntity card) {
        this.id = id;
        this.amount = amount;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", card=" + card +
                '}';
    }
}
