package nl.vue.blocker.vueblocker.movies.domain;

import nl.vue.blocker.vueblocker.movies.acl.layout.PerformanceLayout;
import nl.vue.blocker.vueblocker.movies.acl.reserve.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface Cinema {
    // per day
    Flux<Movie> getMoviesByLocationAndDate(Location location, LocalDate localDate);

    // get later
    Flux<Movie> getFuturePerformancesByLocationAndDate(Location location);

    // now in Vue
    Flux<Movie> getPerformancesByCinema(Location location, LocalDate localDate);

    Flux<Movie> getExpectedMovies(LocalDate localDate, int range);

    PerformanceLayout getPerformanceLayout(String title, String performanceId);

    Flux<Performance> getPerformanceForMovie(int movieId, Location location);

    Reservation reserveSeat(int performanceId, int seatId);

    Reservation unreserveSeat(String sessionId, int performanceId, int seatId);
}
