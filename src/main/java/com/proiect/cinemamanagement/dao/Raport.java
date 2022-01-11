package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class Raport {

    @NotNull private Float receipts;
    @NotNull private Float expenses;
    @NotNull private Float profit;
    @NotNull private LocalDateTime date;

    public Raport(@NotNull Float receipts, @NotNull Float expenses, @NotNull Float profit, @NotNull LocalDateTime date) {
        this.receipts = receipts;
        this.expenses = expenses;
        this.profit = profit;
        this.date = date;
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
        if (!(o instanceof Raport)) return false;
        Raport raport = (Raport) o;
        return receipts.equals(raport.receipts) &&
                expenses.equals(raport.expenses) &&
                profit.equals(raport.profit) &&
                date.equals(raport.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receipts, expenses, profit, date);
    }

    @Override
    public String toString() {
        return "Raport{" +
                "receipts=" + receipts +
                ", expenses=" + expenses +
                ", profit=" + profit +
                ", date=" + date +
                '}';
    }
}
