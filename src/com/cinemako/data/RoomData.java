package com.cinemako.data;

import com.cinemako.enums.SeatStatus;
import com.cinemako.enums.SeatType;
import com.cinemako.model.Room;
import com.cinemako.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class RoomData {

    public static List<Room> getRooms() {

        List<Room> rooms = new ArrayList<>();

        // =====================================================
        // ROOM 1
        // =====================================================

        rooms.add(
                createRoom(
                        "R001",
                        "Cinemako Room 1",
                        5,
                        6
                )
        );

        // =====================================================
        // ROOM 2
        // =====================================================

        rooms.add(
                createRoom(
                        "R002",
                        "Cinemako Room 2",
                        5,
                        6
                )
        );

        // =====================================================
        // VIP ROOM
        // =====================================================

        rooms.add(
                createVipRoom(
                        "R003",
                        "Cinemako VIP Room",
                        4,
                        5
                )
        );

        return rooms;
    }

    private static Room createRoom(
            String roomId,
            String roomName,
            int numberOfRows,
            int seatsPerRow
    ) {

        List<Seat> seats = new ArrayList<>();

        int seatId = 1;

        for (int row = 1; row <= numberOfRows; row++) {

            char rowName = (char) ('A' + row - 1);

            for (int column = 1; column <= seatsPerRow; column++) {

                String seatNumber = rowName + String.valueOf(column);

                seats.add(
                        new Seat(
                                roomId + "-S" + seatId,
                                seatNumber,
                                row,
                                column,
                                SeatType.NORMAL
                        )
                );

                seatId++;
            }
        }

        return new Room(
                roomId,
                roomName,
                seats,
                seats.size()
                );
    }

    private static Room createVipRoom(
            String roomId,
            String roomName,
            int numberOfRows,
            int seatsPerRow
    ) {

        List<Seat> seats = new ArrayList<>();

        int seatId = 1;

        for (int row = 1; row <= numberOfRows; row++) {

            char rowName = (char) ('A' + row - 1);

            for (int column = 1; column <= seatsPerRow; column++) {

                String seatNumber = rowName + String.valueOf(column);

                seats.add(
                        new Seat(
                                roomId + "-S" + seatId,
                                seatNumber,
                                row,
                                column,
                                SeatType.VIP
                        )
                );

                seatId++;
            }
        }

        return new Room(
                roomId,
                roomName,
                seats,
                seats.size()
        );
    }
}
