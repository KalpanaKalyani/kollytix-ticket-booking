# KollyTix - Online Movie & Concert Ticket Booking

Spring Boot based ticket booking web app, deployed on AWS.

## Features
- Movies, re-releases, upcoming and concerts with language filter
- Theatre, date and showtime selection
- Seat selection with auto-pick, tiered pricing, 5-min seat hold
- Snacks, coupons (B1G1, TAMIL50, FIRST10), split bill
- Booking history and ticket with booking code

## Tech Stack
Java 17, Spring Boot, Spring Data JPA, H2 (local), MySQL (AWS RDS), HTML/CSS/JS

## Run locally
    mvnw spring-boot:run

Open http://localhost:8080

## AWS Architecture
EC2 (app server) + RDS MySQL (database) + S3 (poster images) + CloudWatch (monitoring)