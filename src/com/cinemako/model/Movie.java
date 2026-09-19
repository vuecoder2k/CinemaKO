package com.cinemako.model;

import com.cinemako.enums.MovieStatus;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String id;
    private String title;
    private String genre;
    private int duration; // minutes
    private double rating;
    
    private String director;
    private List<String> actors;
    private String description;

    private LocalDate releaseDate;
    private String ageRating;

    private MovieStatus status;

    public Movie(
            String id,
            String title,
            String genre,
            int duration,
            double rating,
            String director,
            List<String> actors,
            String description,
            LocalDate releaseDate,
            String ageRating,
            MovieStatus status) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.ageRating = ageRating;
        this.director = director;
        this.actors = actors;
        this.description = description;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public void displayInfo() {
        System.out.println("ID: " + this.id);
        System.out.println("Tên phim: " + this.title);
        System.out.println("Thể loại: " + this.genre);
        System.out.println("Thời lượng: " + this.duration);
        System.out.println("Đạo diễn: " + this.director);
        System.out.println("Diễn viên: ");
        for(int i = 0; i < actors.size(); i++) {
            System.out.print(actors.get(i));

            if(i + 1 < actors.size())
                System.out.print(", ");
        }
        System.out.println("Ngày phát hành: " + this.releaseDate);
        System.out.println("Trạng thái: ");
        System.out.println("Mô tả: " + this.description);
    }


    public MovieStatus getStatus() {
        return this.status;
    }

    public String getAgeRating() {
         return this.ageRating;
    }

    public double getRating() {
        return this.rating;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDirector() {
        return this.director;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getDuration() {
        return this.duration;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate +
                ", status=" + status +
                '}';
    }
}
