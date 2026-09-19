package com.cinemako.model;

import com.cinemako.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String id;

    private String name;
    private String phone;
    private String email;

    private double balance;

    private List<Ticket> tickets;
    private List<Transaction> transactions;

    public Customer(
            String id,
            String name,
            String phone,
            String email
    ) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;

        this.balance = 0;

        this.tickets = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0."
            );
        }

        balance += amount;

        Transaction transaction = new Transaction(
                "TX" + (transactions.size() + 1),
                this,
                TransactionType.DEPOSIT,
                amount,
                "Deposit money"
        );

        transactions.add(transaction);
    }

    public boolean pay(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (balance < amount) {
            return false;
        }

        balance -= amount;

        Transaction transaction = new Transaction(
                "TX" + (transactions.size() + 1),
                this,
                TransactionType.PAYMENT,
                amount,
                "Ticket payment"
        );

        transactions.add(transaction);

        return true;
    }

    public void refund(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0."
            );
        }

        balance += amount;

        Transaction transaction = new Transaction(
                "TX" + (transactions.size() + 1),
                this,
                TransactionType.REFUND,
                amount,
                "Ticket refund"
        );

        transactions.add(transaction);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
