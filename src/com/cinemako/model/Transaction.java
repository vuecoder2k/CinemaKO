package com.cinemako.model;

import com.cinemako.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {

    private String id;

    private Customer customer;

    private TransactionType type;

    private double amount;

    private LocalDateTime time;

    private String description;

    public Transaction(
            String id,
            Customer customer,
            TransactionType type,
            double amount,
            String description
    ) {
        this.id = id;
        this.customer = customer;
        this.type = type;
        this.amount = amount;
        this.description = description;

        this.time = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", time=" + time +
                ", description='" + description + '\'' +
                '}';
    }
}
