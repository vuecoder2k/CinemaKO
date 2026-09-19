package com.cinemako.data;

import com.cinemako.model.Movie;
import com.cinemako.model.Room;
import com.cinemako.model.ShowTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeData {

    public static List<ShowTime> getShowTimes(
            List<Movie> movies,
            List<Room> rooms
    ) {

        List<ShowTime> showTimes = new ArrayList<>();

        // =====================================================
        // SHOWTIME 1
        // Avengers: Endgame
        // =====================================================

        Movie movie = findMovie(
                movies,
                "M001"
        );

        Room room = findRoom(
                rooms,
                "R001"
        );

        showTimes.add(
                new ShowTime(
                        "ST001",
                        movie,
                        room,
                        LocalDateTime.of(
                                2026, 9, 19,
                                18, 0
                        ),
                        100000
                )
        );

        // =====================================================
        // SHOWTIME 2
        // Avatar: The Way of Water
        // =====================================================

        movie = findMovie(
                movies,
                "M002"
        );

        room = findRoom(
                rooms,
                "R002"
        );

        showTimes.add(
                new ShowTime(
                        "ST002",
                        movie,
                        room,
                        LocalDateTime.of(
                                2026, 9, 19,
                                19, 0
                        ),
                        120000
                )
        );

        // =====================================================
        // SHOWTIME 3
        // Top Gun: Maverick
        // =====================================================

        movie = findMovie(
                movies,
                "M003"
        );

        room = findRoom(
                rooms,
                "R001"
        );

        showTimes.add(
                new ShowTime(
                        "ST003",
                        movie,
                        room,
                        LocalDateTime.of(
                                2026, 9, 19,
                                21, 30
                        ),
                        100000
                )
        );

        // =====================================================
        // SHOWTIME 4
        // Toy Story
        // =====================================================

        movie = findMovie(
                movies,
                "A001"
        );

        room = findRoom(
                rooms,
                "R002"
        );

        showTimes.add(
                new ShowTime(
                        "ST004",
                        movie,
                        room,
                        LocalDateTime.of(
                                2026, 9, 20,
                                10, 0
                        ),
                        70000
                )
        );

        // =====================================================
        // SHOWTIME 5
        // Frozen
        // =====================================================

        movie = findMovie(
                movies,
                "A002"
        );

        room = findRoom(
                rooms,
                "R003"
        );

        showTimes.add(
                new ShowTime(
                        "ST005",
                        movie,
                        room,
                        LocalDateTime.of(
                                2026, 9, 20,
                                14, 0
                        ),
                        150000
                )
        );

        // =====================================================
        // SHOWTIME 6
        // Titanic
        // =====================================================

        movie = findMovie(
                movies,
                "R001"
        );

        room = findRoom(
                rooms,
                "R001"
        );

        showTimes.add(
                new ShowTime(
                        "ST006",
                        movie,
                        room,
                        LocalDateTime.of(
                                2026, 9, 20,
                                18, 30
                        ),
                        100000
                )
        );

        // =====================================================
        // SHOWTIME 7
        // La La Land
        // =====================================================

        movie = findMovie(
                movies,
                "R003"
        );

        room = findRoom(
                rooms,
                "R002"
        );

        showTimes.add(
                new ShowTime(
                        "ST007",
                        movie,
                        room,
                        LocalDateTime.of(
                                2026, 9, 21,
                                19, 0
                        ),
                        90000
                )
        );

        return showTimes;
    }

    private static Movie findMovie(
            List<Movie> movies,
            String movieId
    ) {

        for (Movie movie : movies) {

            if (movie.getId().equals(movieId)) {
                return movie;
            }
        }

        throw new IllegalArgumentException("Movie not found: " + movieId);
    }

    private static Room findRoom(List<Room> rooms, String roomId) {

        for (Room room : rooms) {

            if (room.getId().equals(roomId)) {
                return room;
            }
        }

        throw new IllegalArgumentException("Room not found: " + roomId);
    }
}
