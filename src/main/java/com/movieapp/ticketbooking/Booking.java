package com.movieapp.ticketbooking;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingCode;
    private Long movieId;
    private String movieTitle;
    private String venue;
    private String showTime;
    private String seats;
    private String language;
    private String format;
    private String paymentMethod;
    private String couponUsed;
    private double amount;
    private String bookedAt;
}