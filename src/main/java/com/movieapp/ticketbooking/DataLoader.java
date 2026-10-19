package com.movieapp.ticketbooking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository repo;

    public DataLoader(MovieRepository repo) {
        this.repo = repo;
    }

    private void add(String title, String lang, String langs, String formats, String cat,
                     String venue, String time, double price, String poster, String trailerId,
                     double rating, String duration, String genre, String cast, String desc) {
        Movie m = new Movie();
        m.setTitle(title); m.setLanguage(lang); m.setLanguages(langs); m.setFormats(formats);
        m.setCategory(cat); m.setVenue(venue); m.setShowTime(time); m.setPrice(price);
        m.setPosterUrl(poster); m.setTrailerId(trailerId); m.setRating(rating);
        m.setDuration(duration); m.setGenre(genre); m.setCast(cast); m.setDescription(desc);
        repo.save(m);
    }

    @Override
    public void run(String... args) {
        if (repo.count() > 0) return;

        // ---- NOW SHOWING ----
        add("Jailer 2", "Tamil", "Tamil,Telugu,Hindi,Malayalam,Kannada", "2D,IMAX", "NOW_SHOWING",
                "INOX Madurai", "9:30 PM", 180, "/posters/jailer.jpg", "",
                8.2, "2h 48m", "Action, Comedy",
                "Rajinikanth, Ramya Krishnan, Vinayakan, Mohanlal",
                "Retired jailer oda mass comeback. Family ah protect panna avar edukkura action avatharam.");
        add("Leo", "Tamil", "Tamil,Telugu,Hindi,Malayalam,Kannada", "2D,IMAX", "NOW_SHOWING",
                "PVR Madurai", "7:00 PM", 200, "/posters/leo.jpg", "",
                7.9, "2h 44m", "Action, Thriller",
                "Vijay, Trisha, Sanjay Dutt, Arjun",
                "Oru cafe owner oda amaidhiyaana vaazhkai, past varum bodhu thalai keela aagum.");
        add("Amaran", "Tamil", "Tamil,Telugu,Hindi", "2D", "NOW_SHOWING",
                "Cinepolis Chennai", "6:15 PM", 220, "/posters/amaran.jpg", "",
                8.5, "2h 49m", "Biographical, War",
                "Sivakarthikeyan, Sai Pallavi",
                "Oru raanuva veerar oda unmai kadhai.");
        add("Oppenheimer", "English", "English,Tamil,Hindi", "2D,IMAX", "NOW_SHOWING",
                "PVR Chennai", "8:00 PM", 250, "/posters/oppenheimer.jpg", "",
                8.4, "3h 0m", "Biography, Drama",
                "Cillian Murphy, Emily Blunt, Robert Downey Jr.",
                "Atom bomb uruvakkiya scientist oda kadhai.");

        // ---- COMING SOON ----
        add("Vettaiyan 2", "Tamil", "Tamil,Telugu,Hindi", "2D", "UPCOMING",
                "All Theatres", "Releasing Soon", 0, "/posters/vettaiyan2.jpg", "",
                0, "TBA", "Action", "TBA", "Pudhu release, wait pannunga!");
        add("Mission Impossible 9", "English", "English,Tamil,Hindi", "2D,IMAX", "UPCOMING",
                "All Theatres", "Releasing Soon", 0, "/posters/mi9.jpg", "",
                0, "TBA", "Action, Spy", "Tom Cruise", "Adutha mission varudhu.");

        // ---- RE-RELEASE ----
        add("Ghilli", "Tamil", "Tamil", "2D", "RE_RELEASE",
                "Rohini Chennai", "10:00 AM", 150, "/posters/ghilli.jpg", "",
                8.0, "2h 40m", "Action, Romance", "Vijay, Trisha, Prakash Raj",
                "Kabaddi player oda mass entertainer.");
        add("Theri", "Tamil", "Tamil,Telugu", "2D", "RE_RELEASE",
                "PVR Madurai", "1:00 PM", 150, "/posters/theri.jpg", "",
                7.7, "2h 40m", "Action, Drama", "Vijay, Samantha, Amy Jackson",
                "Oru policeman oda family kadhai.");
        add("Mersal", "Tamil", "Tamil,Telugu", "2D", "RE_RELEASE",
                "INOX Madurai", "4:00 PM", 150, "/posters/mersal.jpg", "",
                7.8, "2h 50m", "Action, Drama", "Vijay, Samantha, Kajal Aggarwal",
                "Medical mafia ku ethiraana moonu Vijay.");
        add("Interstellar", "English", "English,Tamil,Hindi", "2D,IMAX", "RE_RELEASE",
                "PVR Chennai", "5:00 PM", 200, "/posters/interstellar.jpg", "",
                8.7, "2h 49m", "Sci-Fi, Drama", "Matthew McConaughey, Anne Hathaway",
                "Manidha inathai kaapaathum space payanam.");

        // ---- CONCERTS ----
        add("AR Rahman Live", "Tamil", "Tamil,Hindi,English", "Live", "CONCERT",
                "Madurai Stadium", "6:00 PM", 1500, "/posters/arrahman.jpg", "",
                9.0, "3h 0m", "Music", "AR Rahman & Band", "Isai puyal oda live concert.");
        add("Anirudh Live", "Tamil", "Tamil", "Live", "CONCERT",
                "YMCA Chennai", "7:00 PM", 2000, "/posters/anirudh.jpg", "",
                8.8, "3h 0m", "Music", "Anirudh Ravichander", "Rockstar Anirudh oda mass live show.");
        add("Ed Sheeran Live", "English", "English", "Live", "CONCERT",
                "Mumbai Arena", "8:00 PM", 4000, "/posters/edsheeran.jpg", "",
                8.9, "2h 30m", "Music", "Ed Sheeran", "Oru aal, oru guitar, oru perum kootam.");
    }
}