package com.cinemako.model;

import com.cinemako.enums.SeatType;
import com.cinemako.enums.SeatStatus;

public class Seat {
    private String id;
    private String seatNumber;

    private int row;
    private int column;

    private SeatType type;
    private SeatStatus status;

    public Seat(
            String id,
            String seatNumber, 
            int row,
            int column,
            SeatType type
            ) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.row = row;
        this.column = column;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getId() {
        return this.id;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public SeatType getType() {
        return this.type;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.seatNumber + " (" + this.type + ")";
    }
}
