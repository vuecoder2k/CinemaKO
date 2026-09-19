package com.cinemako.model;

import com.cinemako.enums.SeatType;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String name;

    private List<Seat> seats;

    private int capacity;

    public Room(String id, String name, List<Seat> seats, int capacity) {
        this.id = id;
        this.name = name;
        this.seats = seats;
        this.capacity = capacity;
    }
    
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Seat findSeat(String seatNumber) {
        for(Seat seat : seats) {
            if(seat.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    public int getVipSeatCount() {
        int count = 0;
        for(Seat seat : seats) {
            if(seat.getType() == SeatType.VIP) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + getCapacity() +
                '}';
    }
}
