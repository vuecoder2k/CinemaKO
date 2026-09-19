package com.cinemako.model;

import com.cinemako.enums.SeatStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ShowTime {
    private String id;

    private Movie movie;
    private Room room;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private double ticketPrice;
    private Set<String> bookedSeats;
    
    public ShowTime(
            String id,
            Movie movie,
            Room room,
            LocalDateTime startTime,
            double ticketPrice
            ) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
        this.ticketPrice = ticketPrice;

        this.endTime = startTime.plusMinutes(movie.getDuration());
        this.bookedSeats = new HashSet<>();
    }

    public String getId() {
        return this.id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public Room getRoom() {
        return this.room;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }
    
    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public Set<String> getBookedSeats() {
        return this.bookedSeats;
    }

    public int getAvailableSeatCount() {
        int count = 0;

        for(Seat seat : room.getSeats()) {
            if(seat.getStatus() == SeatStatus.AVAILABLE) {
                count++;
            }
        }
        return count;
    }

    // Kiểm tra các ghế còn trống
    public boolean isSeatAvailable(String seatNumber) {
        Seat seat = room.findSeat(seatNumber);

        if(seat == null) {
            return false;
        }
        if(seat.getStatus() == SeatStatus.MAINTENANCE) {
            return false;
        }
        return !bookedSeats.contains(seatNumber.toUpperCase());
    }

    // Đặt ghế
    public boolean bookSeat(String seatNumber) {
        seatNumber = seatNumber.toUpperCase();

        if(!isSeatAvailable(seatNumber)) {
            return false;
        }
        
        bookedSeats.add(seatNumber);

        return true;
    }

    //Kiểm tra ghế này đã có người đặt chưa?
    public boolean isBookedSeat(String seatNumber) {
        if(!isSeatAvailable(seatNumber)) {
            return false;
        }
        if(bookedSeats.contains(seatNumber)) {
            return false;
        }
        return true;
    }

    // Huỷ đặt ghế
    public boolean cancelSeat(String seatNumber) {
        return bookedSeats.remove(seatNumber.toUpperCase());
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "id='" + id + '\'' +
                ", movie=" + movie.getTitle() +
                ", room=" + room.getName() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
