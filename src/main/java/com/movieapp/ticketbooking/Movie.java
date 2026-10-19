package com.movieapp.ticketbooking;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Entity
@Getter @Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private String languages;   // "Tamil,Telugu,Hindi"
    private String formats;     // "2D,3D,IMAX"
    private String category;    // NOW_SHOWING, UPCOMING, RE_RELEASE, CONCERT
    private String venue;
    private String showTime;
    private double price;
    private int totalSeats = 50;
    private String bookedSeats = "";
    private String posterUrl;
    private String trailerId;
    private double rating;
    private String duration;
    private String genre;
    @Column(length = 1000)
    private String description;
    @Column(name = "cast_members")
    private String cast;

    public List<String> getBookedSeatList() {
        if (bookedSeats == null || bookedSeats.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(bookedSeats.split(",")));
    }

    public int getAvailableSeats() {
        return totalSeats - getBookedSeatList().size();
    }
}