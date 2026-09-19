package com.cinemako.model;

import com.cinemako.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private final String id;
    private final Customer customer;
    private final ShowTime showTime;

    private final List<String> seats;

    private final LocalDateTime bookingTime;

    private final double totalPrice;
    private TicketStatus status;

    public Ticket(
            String id,
            Customer customer,
            ShowTime showTime,
            List<String> seats) {
        this.id = id;
        this.customer = customer;
        this.showTime = showTime;
        this.seats = new ArrayList<>(seats);
        this.bookingTime = LocalDateTime.now();
        this.totalPrice = showTime.getTicketPrice() * seats.size();
        this.status = TicketStatus.CONFIRMED;
    }

    public String getId() {
        return this.id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public ShowTime getShowTime() {
        return this.showTime;
    }

    public LocalDateTime getBookingTime() {
        return this.bookingTime;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public TicketStatus getStatus() {
        return this.status;
    }

    public List<String> getSeats() {
        return this.seats;
    }
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
    public void cancel() {
        this.status = TicketStatus.CANCELLED;
    }

    public void use() {
        this.status = TicketStatus.USED;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", customer=" + customer.getName() +
                ", movie=" + showTime.getMovie().getTitle() +
                ", seats=" + seats +
                ", startTime=" + showTime.getStartTime() +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
