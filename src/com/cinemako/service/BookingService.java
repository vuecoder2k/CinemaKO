package com.cinemako.service;

import com.cinemako.model.*;
import com.cinemako.enums.TicketStatus;
import java.util.List;

public class BookingService {

    public String bookTicket(Customer customer, ShowTime showTime, List<String> seats, double totalPrice) {
        if (!customer.pay(totalPrice)) {
            return "Số dư không đủ!";
        }

        for (String seat : seats) {
            showTime.bookSeat(seat);
        }

        Ticket ticket = new Ticket(
                "T" + System.currentTimeMillis(),
                customer,
                showTime,
                seats
        );

        customer.addTicket(ticket);
        return "SUCCESS:" + ticket.getId();
    }

    public String cancelTicket(Customer customer, Ticket ticket) {
        if (ticket.getStatus() == TicketStatus.CANCELLED) {
            return "Vé này đã được hủy trước đó.";
        }

        if (ticket.getStatus() == TicketStatus.USED) {
            return "Vé đã được sử dụng, không thể hủy.";
        }

        // 1. Trả ghế
        ShowTime showTime = ticket.getShowTime();
        for (String seat : ticket.getSeats()) {
            showTime.cancelSeat(seat);
        }

        // 2. Hoàn tiền
        customer.refund(ticket.getTotalPrice());

        // 3. Đổi trạng thái vé
        ticket.setStatus(TicketStatus.CANCELLED);

        return "SUCCESS";
    }

    public Ticket findTicket(List<Ticket> tickets, String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getId().equalsIgnoreCase(ticketId)) {
                return ticket;
            }
        }
        return null;
    }
}
