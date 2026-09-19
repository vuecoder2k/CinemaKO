package com.cinemako.data;

import com.cinemako.enums.MovieStatus;
import com.cinemako.model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieData {

    public static List<Movie> getMovies() {

        List<Movie> movies = new ArrayList<>();

        // =========================================================
        // BLOCKBUSTER
        // =========================================================

        movies.add(new Movie(
                "M001",
                "Avengers: Endgame",
                "Action",
                181,
                8.4,
                "Anthony Russo, Joe Russo",
                new ArrayList<>(Arrays.asList(
                        "Robert Downey Jr.",
                        "Chris Evans",
                        "Mark Ruffalo",
                        "Chris Hemsworth"
                )),
                "The Avengers reunite to reverse the devastating consequences of Thanos.",
                LocalDate.of(2019, 4, 26),
                "PG-13",
                MovieStatus.NOW_SHOWING
        ));

        movies.add(new Movie(
                "M002",
                "Avatar: The Way of Water",
                "Action, Adventure, Sci-Fi",
                192,
                7.5,
                "James Cameron",
                new ArrayList<>(Arrays.asList(
                        "Sam Worthington",
                        "Zoe Saldana",
                        "Sigourney Weaver"
                )),
                "Jake Sully and his family explore the oceans of Pandora while facing a new threat.",
                LocalDate.of(2022, 12, 16),
                "PG-13",
                MovieStatus.UPCOMING
        ));

        movies.add(new Movie(
                "M003",
                "Top Gun: Maverick",
                "Action, Drama",
                130,
                8.2,
                "Joseph Kosinski",
                new ArrayList<>(Arrays.asList(
                        "Tom Cruise",
                        "Miles Teller",
                        "Jennifer Connelly"
                )),
                "Maverick trains a new generation of pilots for an extremely dangerous mission.",
                LocalDate.of(2022, 5, 27),
                "PG-13",
                MovieStatus.ENDED
        ));

        movies.add(new Movie(
                "M004",
                "Jurassic World",
                "Action, Adventure, Sci-Fi",
                124,
                6.9,
                "Colin Trevorrow",
                new ArrayList<>(Arrays.asList(
                        "Chris Pratt",
                        "Bryce Dallas Howard",
                        "Ty Simpkins"
                )),
                "A dinosaur theme park falls into chaos after genetically engineered dinosaurs escape.",
                LocalDate.of(2015, 6, 12),
                "PG-13",
                MovieStatus.ENDED
        ));

        movies.add(new Movie(
                "M005",
                "The Dark Knight",
                "Action, Crime, Drama",
                152,
                9.0,
                "Christopher Nolan",
                new ArrayList<>(Arrays.asList(
                        "Christian Bale",
                        "Heath Ledger",
                        "Aaron Eckhart"
                )),
                "Batman faces the Joker, a criminal mastermind who pushes Gotham into chaos.",
                LocalDate.of(2008, 7, 18),
                "PG-13",
                MovieStatus.ENDED
        ));

        // =========================================================
        // ANIMATION
        // =========================================================

        movies.add(new Movie(
                "A001",
                "Toy Story",
                "Animation, Adventure, Comedy",
                81,
                8.3,
                "John Lasseter",
                new ArrayList<>(Arrays.asList(
                        "Tom Hanks",
                        "Tim Allen",
                        "Don Rickles"
                )),
                "A group of toys comes to life whenever their owner leaves the room.",
                LocalDate.of(1995, 11, 22),
                "G",
                MovieStatus.ENDED
        ));

        movies.add(new Movie(
                "A002",
                "Frozen",
                "Animation, Adventure, Comedy",
                102,
                7.4,
                "Chris Buck, Jennifer Lee",
                new ArrayList<>(Arrays.asList(
                        "Kristen Bell",
                        "Idina Menzel",
                        "Jonathan Groff"
                )),
                "A young princess sets out to find her sister and save their kingdom from eternal winter.",
                LocalDate.of(2013, 11, 27),
                "PG",
                MovieStatus.NOW_SHOWING
        ));

        movies.add(new Movie(
                "A003",
                "The Lion King",
                "Animation, Adventure, Drama",
                88,
                8.5,
                "Roger Allers, Rob Minkoff",
                new ArrayList<>(Arrays.asList(
                        "Matthew Broderick",
                        "Jeremy Irons",
                        "James Earl Jones"
                )),
                "A young lion must overcome tragedy and take his rightful place as king.",
                LocalDate.of(1994, 6, 24),
                "G",
                MovieStatus.ENDED
        ));

        movies.add(new Movie(
                "A004",
                "Finding Nemo",
                "Animation, Adventure, Comedy",
                100,
                8.2,
                "Andrew Stanton",
                new ArrayList<>(Arrays.asList(
                        "Albert Brooks",
                        "Ellen DeGeneres",
                        "Alexander Gould"
                )),
                "A father travels across the ocean to find his lost son.",
                LocalDate.of(2003, 5, 30),
                "G",
                MovieStatus.ENDED
        ));

        movies.add(new Movie(
                "A005",
                "Inside Out",
                "Animation, Adventure, Comedy",
                95,
                8.1,
                "Pete Docter",
                new ArrayList<>(Arrays.asList(
                        "Amy Poehler",
                        "Phyllis Smith",
                        "Richard Kind"
                )),
                "Inside the mind of a young girl, emotions struggle to guide her through a major life change.",
                LocalDate.of(2015, 6, 19),
                "PG",
                MovieStatus.ENDED
        ));

        // =========================================================
        // ROMANCE
        // =========================================================

        movies.add(new Movie(
                "R001",
                "Titanic",
                "Romance, Drama",
                195,
                7.9,
                "James Cameron",
                new ArrayList<>(Arrays.asList(
                        "Leonardo DiCaprio",
                        "Kate Winslet",
                        "Billy Zane"
                )),
                "A young couple from different social classes fall in love aboard the Titanic.",
                LocalDate.of(1997, 12, 19),
                "PG-13",
                MovieStatus.ENDED
        ));

        movies.add(new Movie(
                "R002",
                "The Notebook",
                "Romance, Drama",
                123,
                7.8,
                "Nick Cassavetes",
                new ArrayList<>(Arrays.asList(
                        "Ryan Gosling",
                        "Rachel McAdams",
                        "James Garner"
                )),
                "A man reads a story about a young couple whose love survives many obstacles.",
                LocalDate.of(2004, 6, 25),
                "PG-13",
                MovieStatus.UPCOMING
        ));

        movies.add(new Movie(
                "R003",
                "La La Land",
                "Romance, Drama, Music",
                128,
                8.0,
                "Damien Chazelle",
                new ArrayList<>(Arrays.asList(
                        "Ryan Gosling",
                        "Emma Stone",
                        "John Legend"
                )),
                "A musician and an aspiring actress fall in love while pursuing their dreams in Los Angeles.",
                LocalDate.of(2016, 12, 9),
                "PG-13",
                MovieStatus.NOW_SHOWING
        ));

        movies.add(new Movie(
                "R004",
                "Me Before You",
                "Romance, Drama",
                110,
                7.4,
                "Thea Sharrock",
                new ArrayList<>(Arrays.asList(
                        "Emilia Clarke",
                        "Sam Claflin",
                        "Janet McTeer"
                )),
                "A young woman becomes a caregiver and develops an unexpected relationship with her patient.",
                LocalDate.of(2016, 6, 3),
                "PG-13",
                MovieStatus.ENDED
        ));

        movies.add(new Movie(
                "R005",
                "Crazy Rich Asians",
                "Romance, Comedy, Drama",
                120,
                6.9,
                "Jon M. Chu",
                new ArrayList<>(Arrays.asList(
                        "Constance Wu",
                        "Henry Golding",
                        "Michelle Yeoh"
                )),
                "A woman discovers that her boyfriend comes from one of the richest families in Singapore.",
                LocalDate.of(2018, 8, 15),
                "PG-13",
                MovieStatus.ENDED
        ));

        return movies;
    }
}
