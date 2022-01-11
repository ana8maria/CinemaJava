package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Raport;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="raport")
public class RaportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name = "receipts")
    private Float receipts;
    @Column(name="expenses")
    private Float expenses;
    @Column(name="profit")
    private Float profit;

    @Column(name="date")
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "employee_raport",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "raport_id"))
    private Set<EmployeeEntity> employee = new HashSet<>();

    public RaportEntity(Raport raport){
        this.receipts=raport.getReceipts();
        this.date=raport.getDate();
        this.expenses=raport.getExpenses();
        this.profit=raport.getProfit();
    }

    public RaportEntity(int id, Float receipts, Float expenses, Float profit, LocalDateTime date) {
        this.id = id;
        this.receipts = receipts;
        this.expenses = expenses;
        this.profit = profit;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getReceipts() {
        return receipts;
    }

    public void setReceipts(Float receipts) {
        this.receipts = receipts;
    }

    public Float getExpenses() {
        return expenses;
    }

    public void setExpenses(Float expenses) {
        this.expenses = expenses;
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RaportEntity)) return false;
        RaportEntity that = (RaportEntity) o;
        return id == that.id &&
                receipts.equals(that.receipts) &&
                expenses.equals(that.expenses) &&
                profit.equals(that.profit) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, receipts, expenses, profit, date);
    }

    @Override
    public String toString() {
        return "RaportEntity{" +
                "id=" + id +
                ", receipts=" + receipts +
                ", expenses=" + expenses +
                ", profit=" + profit +
                ", date=" + date +
                '}';
    }
}
