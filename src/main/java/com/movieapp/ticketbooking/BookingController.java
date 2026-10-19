package com.movieapp.ticketbooking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {

    public record BookingRequest(Long movieId, List<String> seats, String language,
                                 String format, String paymentMethod,
                                 double snackAmount, String coupon) {}

    private final MovieRepository movieRepo;
    private final BookingRepository bookingRepo;

    public BookingController(MovieRepository movieRepo, BookingRepository bookingRepo) {
        this.movieRepo = movieRepo;
        this.bookingRepo = bookingRepo;
    }

    private double seatPrice(double base, String seat) {
        int row = seat.charAt(0) - 'A';
        double mult = row < 2 ? 1.5 : row < 5 ? 1.2 : 1.0;
        return Math.round(base * mult);
    }

    @GetMapping
    public List<Booking> all() {
        return bookingRepo.findAllByOrderByIdDesc();
    }

    @PostMapping
    public synchronized ResponseEntity<Object> create(@RequestBody BookingRequest r) {
        Movie m = movieRepo.findById(r.movieId()).orElseThrow();
        List<String> booked = m.getBookedSeatList();

        if (r.seats() == null || r.seats().isEmpty())
            return ResponseEntity.badRequest().body("Seat select pannala");

        for (String s : r.seats()) {
            if (booked.contains(s))
                return ResponseEntity.status(409).body("Seat " + s + " already booked");
        }

        double total = r.snackAmount();
        for (String s : r.seats()) total += seatPrice(m.getPrice(), s);

        String coupon = r.coupon() == null ? "" : r.coupon().trim().toUpperCase();
        double discount = 0;
        if (coupon.equals("TAMIL50")) discount = 50;
        else if (coupon.equals("FIRST10")) discount = total * 0.10;
        else if (coupon.equals("B1G1")) {
            if (r.seats().size() < 2)
                return ResponseEntity.badRequest().body("B1G1 ku minimum 2 seats venum");
            double cheapest = Double.MAX_VALUE;
            for (String s : r.seats()) cheapest = Math.min(cheapest, seatPrice(m.getPrice(), s));
            discount = cheapest;
        }
        else if (!coupon.isEmpty()) return ResponseEntity.badRequest().body("Invalid coupon");
        total = Math.max(0, Math.round(total - discount));

        booked.addAll(r.seats());
        m.setBookedSeats(String.join(",", booked));
        movieRepo.save(m);

        Booking b = new Booking();
        b.setBookingCode("BMT" + (100000 + new Random().nextInt(900000)));
        b.setMovieId(m.getId());
        b.setMovieTitle(m.getTitle());
        b.setVenue(m.getVenue());
        b.setShowTime(m.getShowTime());
        b.setSeats(String.join(", ", r.seats()));
        b.setLanguage(r.language());
        b.setFormat(r.format());
        b.setPaymentMethod(r.paymentMethod());
        b.setCouponUsed(coupon);
        b.setAmount(total);
        b.setBookedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")));
        return ResponseEntity.ok(bookingRepo.save(b));
    }
}