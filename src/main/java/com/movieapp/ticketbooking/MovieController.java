package com.movieapp.ticketbooking;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieRepository.findById(id).orElseThrow();
    }
}